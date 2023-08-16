package com.bonobono.backend.member.service;

import com.bonobono.backend.auth.jwt.TokenProvider;
import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import com.bonobono.backend.character.repository.UserCharacterRepository;
import com.bonobono.backend.global.service.AwsS3Service;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import com.bonobono.backend.member.domain.Token;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.bonobono.backend.member.dto.request.*;
import com.bonobono.backend.member.dto.response.LoginResponseDto;
import com.bonobono.backend.member.dto.response.MemberProfileResponseDto;
import com.bonobono.backend.member.dto.response.MemberResponseDto;
import com.bonobono.backend.member.dto.response.ProfileImgResponseDto;
import com.bonobono.backend.member.dto.response.TokenDto;
import com.bonobono.backend.global.exception.AppException;
import com.bonobono.backend.global.exception.ErrorCode;
import com.bonobono.backend.member.repository.AuthorityRepository;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.member.repository.ProfileImgRepository;
import com.bonobono.backend.member.repository.TokenRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthorityRepository authorityRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final OurCharacterRepository ourCharacterRepository;
    private final ProfileImgRepository imgRepository;
    private final AwsS3Service awsS3Service;

    /**
     * 현재 로그인한 사용자 조회
     */
    public Member getMemberById(Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return member;
        } else {
            throw new NotFoundException("해당 멤버를 찾을 수 없습니다.");
        }
    }

    /**
     * 회원가입
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto request) {

        memberRepository.findByUsername(request.getUsername())
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 존재하는 아이디입니다.");
                });

        memberRepository.findByNickname(request.getNickname())
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.NICKNAME_DUPLICATED, "이미 존재하는 닉네임입니다.");
                });

        Authority authority = authorityRepository
                .findByRole(Role.USER)
                .orElseThrow(() -> new RuntimeException("권한 정보가 없습니다."));

        Set<Authority> set = new HashSet<>();
        set.add(authority);

        Member member = request.toMember(bCryptPasswordEncoder, set);

        ProfileImg profileImg = ProfileImg.builder()
            .imgUrl("")
            .imgName("")
            .member(member)
            .build();

        imgRepository.save(profileImg);

        Long DefaultCharId =14L;

        OurCharacter ourCharacter = ourCharacterRepository.findById(DefaultCharId)
                .orElseThrow(()-> new IllegalArgumentException("돌고래 level 1이 없습니다 ="+DefaultCharId));

        UserCharacter userCharacter= UserCharacter.builder()
                .customName("기본 돌고래")
                .ourCharacter(ourCharacter)
                .main(true)
                .locationName("")
                .member(member)
                .build();

        userCharacterRepository.save(userCharacter);

        return MemberResponseDto.of(memberRepository.save(member));
    }

    /**
     * 로그인
     */
    @Transactional
    public LoginResponseDto login(MemberLoginRequestDto request) {

        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USERNAME_NOTFOUND, "존재하지 않는 아이디입니다.");
                });

        if (!bCryptPasswordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "잘못된 비밀번호입니다.");
        }

        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        Long memberId = member.getId();

        Set<Authority> role = member.getRole();

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        LoginResponseDto loginResponseDto = new LoginResponseDto(memberId, tokenDto, role);

        Token refreshToken = Token.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .fcmtoken(request.getFcmtoken())
                .build();

        tokenRepository.save(refreshToken);

        return loginResponseDto;
    }

    /**
     * 토큰 재발급
     */
    @Transactional
    public TokenDto reissue(TokenRequestDto request) {

        if (!tokenProvider.validateToken(request.getRefreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(request.getAccessToken());

        Token refreshToken = tokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        if (!refreshToken.getValue().equals(request.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        Token newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        tokenRepository.save(newRefreshToken);

        return tokenDto;
    }

    /**
     * 회원 정보 조회
     */
    @Transactional(readOnly = true)
    public MemberProfileResponseDto myProfile() {

        Member member = memberRepository.findById(SecurityUtil.getLoginMemberId())
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 멤버입니다."));

        List<UserCharacter> userCharacters = userCharacterRepository.findByMemberId(member.getId());
        int experience = userCharacters.stream()
            .mapToInt(UserCharacter::getExperience)
            .sum();

        MemberRequestDto request = MemberRequestDto.builder()
            .memberId(member.getId())
            .name(member.getName())
            .nickname(member.getNickname())
            .username(member.getUsername())
            .phoneNumber(member.getPhoneNumber())
            .role(member.getRole())
            .build();

        return MemberProfileResponseDto.builder()
            .member(request)
            .experience(experience)
            .build();
    }

    /**
     * 회원 정보 수정 (아이디 수정 불가)
     */
    @Transactional
    public void updateMyInfo(MemberUpdateRequestDto dto) {

        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        member.updateMember(dto);
    }

    /**
     * 비밀번호 수정
     */
    @Transactional
    public void passwordChange(PasswordChangeRequestDto request) {

        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        member.changePassword(request, bCryptPasswordEncoder);
    }

    /**
     * 프로필 이미지 저장
     */
    public ProfileImgResponseDto saveProfileImg(Member member, MultipartFile img, String imageDirName) {

        String imageUrl = awsS3Service.upload(img, imageDirName).getPath();

        ProfileImgRequestDto request = ProfileImgRequestDto.builder()
            .imgName(img.getOriginalFilename())
            .imgUrl(imageUrl)
            .build();

        ProfileImg profileImg = imgRepository
                .findByMember(member)
                .orElse(null);

        if (profileImg.equals("")) {
            profileImg = request.toEntity(member);
        } else {
            profileImg.updateImage(request);
        }

        imgRepository.save(profileImg);

        ProfileImgResponseDto response = ProfileImgResponseDto.builder()
            .memberId(member.getId())
            .img(request)
            .build();

        return response;
    }

    /**
     * 프로필 이미지 수정
     */
    public ProfileImgResponseDto uploadProfileImg(Member member, MultipartFile newImage, ProfileImg oldImage, String imageDirName) {

        if (oldImage.getImageName().equals("") && oldImage.getImageUrl().equals("")) {
            ProfileImgResponseDto response = saveProfileImg(member, newImage, imageDirName);

            return response;
        } else {

            String imageUrl = oldImage.getImageUrl();

            deleteProfileImg(oldImage, imageUrl, imageDirName);
            ProfileImgResponseDto response = saveProfileImg(member, newImage, imageDirName);
            return response;
        }
    }

    /**
     * 프로필 이미지 삭제
     */
    public void deleteProfileImg(ProfileImg profileImg, String imageUrl, String dirName) {

        awsS3Service.delete(imageUrl, dirName);

        ProfileImgRequestDto request = ProfileImgRequestDto.builder()
                .imgName("")
                .imgUrl("")
                .build();

        profileImg.updateImage(request);

        imgRepository.save(profileImg);
    }

    /**
     * 로그아웃
     */
    @Transactional
    public void logout(HttpServletRequest request) {

        // 로그아웃 시 해당 멤버가 로그인 시 발급받은 accessToken을 다른 Table에 등록
        // 추 후 Table에 등록된 accessToken으로 요청을 보낼 시에 로그아웃된 사용자로 인식
        // 나중에 해당 로직 구현해야할 필요가 있음

        tokenRepository.deleteByKey(String.valueOf(SecurityUtil.getLoginMemberId()))
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 회원탈퇴
     */
    @Transactional
    public void deleteMember() {

        Long loginMemberId = SecurityUtil.getLoginMemberId();

        if (loginMemberId == null) {
            throw new RuntimeException("로그인 유저 정보가 없습니다.");
        }

        memberRepository.deleteById(loginMemberId);
    }
}