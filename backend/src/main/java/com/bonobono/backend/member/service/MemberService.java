package com.bonobono.backend.member.service;

import com.bonobono.backend.member.entity.Member;
import com.bonobono.backend.member.exception.AppException;
import com.bonobono.backend.member.exception.ErrorCode;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.member.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret-key}")
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60l; // 1시간

    public String signup(String name, String nickname, String accountId, String password, String phoneNumber) {

        // 중복 check : nickname, accountId, phoneNumber
//        memberRepository.findByNickname(nickname)
//            .ifPresent(member -> {
//                throw new RuntimeException("이미 존재하는 닉네임입니다.");
//            });

        memberRepository.findByAccountId(accountId)
            .ifPresent(member -> {
                throw new AppException(ErrorCode.ACCOUNTID_DUPLICATED, "중복되는 아이디입니다.");
            });

//        memberRepository.findByPhoneNumber(phoneNumber)
//            .ifPresent(member -> {
//                throw new RuntimeException("이미 가입한 휴대번호 입니다.");
//            });

        // 저장
        Member member = Member.builder()
            .name(name)
            .nickname(nickname)
            .accountId(accountId)
            .password(encoder.encode(password))
            .phoneNumber(phoneNumber)
            .build();

        memberRepository.save(member);

        return "SUCCESS";
    }

    public String login(String accountId, String password) {
        // accountId 없음
        Member selectedMember = memberRepository.findByAccountId(accountId)
            .orElseThrow(() -> new AppException(ErrorCode.ACCOUNTID_NOTFOUND, "존재하지 않는 아이디입니다."));

        // password 틀림
        if(!encoder.matches(password, selectedMember.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "비밀번호를 잘못 입력했습니다.");
        }

        String token = JwtUtil.createToken(selectedMember.getAccountId(), key, expireTimeMs);

        // 로그인 성공 -> 토큰 발행
        return token;
    }
}
