package com.bonobono.backend.member.service;

import com.bonobono.backend.member.entity.Member;
import com.bonobono.backend.member.exception.AppException;
import com.bonobono.backend.member.exception.ErrorCode;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

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
            .password(password)
            .phoneNumber(phoneNumber)
            .build();

        memberRepository.save(member);

        return "SUCCESS";
    }
}
