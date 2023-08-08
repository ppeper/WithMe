package com.bonobono.backend.character.service;

import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OurCharacterService {

    private final MemberRepository memberRepository;
    private final OurCharacterRepository ourCharacterRepository;

    @Transactional(readOnly = true)
    public List<OurCharacterResponseDto> OurfindByList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        return ourCharacterRepository.findNotLinkedOurCharactersByMember(member.getId())
                .stream()
                .map(OurCharacterResponseDto::new)
                .collect(Collectors.toList());
    }
}
