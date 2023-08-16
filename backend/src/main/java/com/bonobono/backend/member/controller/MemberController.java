package com.bonobono.backend.member.controller;

import com.bonobono.backend.global.exception.AppException;
import com.bonobono.backend.global.exception.ErrorCode;
import com.bonobono.backend.global.service.AwsS3Service;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import com.bonobono.backend.member.dto.request.*;
import com.bonobono.backend.member.dto.response.LoginResponseDto;
import com.bonobono.backend.member.dto.response.MemberProfileResponseDto;
import com.bonobono.backend.member.dto.response.MemberResponseDto;
import com.bonobono.backend.member.dto.response.ProfileImgResponseDto;
import com.bonobono.backend.member.dto.response.TokenDto;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.member.repository.ProfileImgRepository;
import com.bonobono.backend.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "member", description = "사용자")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Operation(
            summary = "회원가입",
            description = "아이디, 실명, 닉네임, 비밀번호, 휴대폰번호를 입력하여 회원가입을 통해 계정을 생성합니다."
    )
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {

        return ResponseEntity.ok(memberService.signup(memberRequestDto));
    }

    @Operation(
            summary = "아이디 중복체크",
            description = "username을 입력하여 이미 존재하는 아이디인지 아닌지 중복체크를 진행합니다."
    )
    @PostMapping("/username")
    public boolean username(@RequestBody MemberUsernameRequestDto request) {

        memberRepository.findByUsername(request.getUsername())
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 존재하는 아이디입니다.");
                });

        return true;
    }

    @Operation(
            summary = "닉네임 중복체크",
            description = "nickname을 입력하여 이미 존재하는 닉네임인지 아닌지 중복체크를 진행합니다."
    )
    @PostMapping("/nickname")
    public boolean nickname(@RequestBody MemberNicknameRequestDto request) {

        memberRepository.findByNickname(request.getNickname())
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.NICKNAME_DUPLICATED, "이미 존재하는 닉네임입니다.");
                });

        return true;
    }

    @Operation(
            summary = "로그인",
            description = "username과 password를 입력받아 로그인한 사용자에게 accessToken과 refreshToken을 반환해줍니다."
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody MemberLoginRequestDto memberRequestDto) {

        return ResponseEntity.ok(memberService.login(memberRequestDto));
    }

    @Operation(
            summary = "토큰 재발급",
            description = "로그인을 했을 때 발급받은 만료기간이 지난 accessToken과 refreshToken을 입력하여 accessToken과 refreshToken을 재발급해줍니다."
    )
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {

        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }

    @Operation(
            summary = "프로필 조회",
            description = "로그인을 했을 때 발급받은 accessToken을 헤더에 넣어 해당 사용자의 프로필을 조회합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<MemberProfileResponseDto> myProfile() {

        return ResponseEntity.ok(memberService.myProfile());
    }

    @Operation(
            summary = "프로필 수정",
            description = "로그인을 했을 때 발급받은 accessToken을 헤더에 넣어 해당 사용자의 실명, 닉네임, 휴대폰번호를 수정할 수 있습니다."
    )
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update")
    public ResponseEntity<MemberProfileResponseDto> updateMyInfo(@RequestBody MemberUpdateRequestDto dto) {

        memberService.updateMyInfo(dto);

        return ResponseEntity.ok(memberService.myProfile());
    }

    @Operation(
        summary = "프로필 이미지 조회",
        description = "로그인을 했을 때 발급받은 accessToken을 헤더에 넣고, 해당 멤버의 프로필 이미지를 조회할 수 있습니다."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/img")
    public ResponseEntity<ProfileImgResponseDto> getImg() {

        Member member = memberService.getMemberById(SecurityUtil.getLoginMemberId());
        ProfileImg img = member.getProfileImg();

        ProfileImgResponseDto response = ProfileImgResponseDto.builder()
            .memberId(member.getId())
            .img(img.toRequestDto())
            .build();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "프로필 이미지 업로드",
            description = "로그인을 했을 때 발급받은 accessToken을 헤더에 넣고, 이미지를 body에서 multipart 타입으로 넣어 요청을 보내면 해당 이미지로 프로필 이미지를 설정할 수 있습니다."
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileImgResponseDto> uploadImg(
            @RequestPart("image") MultipartFile image
    ) {

        Member member = memberService.getMemberById(SecurityUtil.getLoginMemberId());
        ProfileImg oldImg = member.getProfileImg();
        String imgDirName = "profile_images";

        return ResponseEntity.ok(memberService.uploadProfileImg(member, image, oldImg, imgDirName));
    }

    @Operation(
            summary = "프로필 이미지 삭제",
            description = "요청을 보내면 프로필 이미지를 삭제합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/img")
    public ResponseEntity<String> deleteImg() {

        Member member = memberService.getMemberById(SecurityUtil.getLoginMemberId());
        ProfileImg img = member.getProfileImg();
        String url = img.getImageUrl();
        String imgDirName = "profile_images";

        memberService.deleteProfileImg(img, url, imgDirName);

        return new ResponseEntity<>("프로필 삭제 성공", HttpStatus.OK);
    }

    @Operation(
            summary = "비밀번호 변경",
            description = "로그인을 했을 때 발급받은 accessToken을 헤더에 넣어 해당 사용자의 비밀번호를 변경할 수 있습니다."
    )
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password")
    public ResponseEntity<MemberProfileResponseDto> passwordChange(@RequestBody PasswordChangeRequestDto dto) {

        memberService.passwordChange(dto);

        return ResponseEntity.ok(memberService.myProfile());
    }

    @Operation(
            summary = "회원탈퇴",
            description = "로그인을 했을 때 발급받은 accessToken을 헤더에 넣어 해당 사용자를 로그아웃 처리해준 뒤 데이터베이스에서 해당 사용자의 정보를 삭제합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteMember(HttpServletRequest request) {

        memberService.logout(request);
        memberService.deleteMember();

        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
    }

    @Operation(
            summary = "로그아웃",
            description = "로그인을 했을 때 발급받은 accessToken을 헤더에 넣어 해당 사용자를 로그아웃 처리해줍니다."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        memberService.logout(request);

        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}