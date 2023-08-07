package com.bonobono.backend.dailymission.service;


import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.dailymission.domain.IsMiniGame;
import com.bonobono.backend.dailymission.domain.MiniGame;
import com.bonobono.backend.dailymission.dto.MiniGameRequestDto;
import com.bonobono.backend.dailymission.dto.MiniGameResponseDto;
import com.bonobono.backend.dailymission.repository.IsMiniGameRepository;
import com.bonobono.backend.dailymission.repository.MiniGameRepository;
import com.bonobono.backend.global.exception.MainCharacterNotFoundException;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class MiniGameService {
    private final MemberRepository memberRepository;
    private final MiniGameRepository miniGameRepository;
    private final IsMiniGameRepository isMiniGameRepository;

    LocalDate checkDate = LocalDate.now();

    //mini게임참여여부 체크하고, minigame랜덤으로 생성해서 전달
    @Transactional
    public MiniGameResponseDto checkMiniGame(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("해당 멤버가 존재하지 않습니다 +id"+memberId));

        if (isMiniGameRepository.existsByMemberIdAndCheckDate(memberId,checkDate)) {
            log.trace("이미 게임에 참여했습니다");
            System.out.println("이미 게임에 참여했습니다");
            return new MiniGameResponseDto(null,null);
        }

        // 미니게임랜덤 생성
        long qty = miniGameRepository.findAll().size();
        int idx = (int) (Math.random()*qty);
        Page<MiniGame> miniGamePage = miniGameRepository.findAll(PageRequest.of(idx,1, Sort.by("id")));
        MiniGame miniGame=null;
        if (miniGamePage.hasContent()) {miniGame = miniGamePage.getContent().get(0);}
        else {throw new IllegalStateException("미니게임을 찾을 수 없습니다");}

        //미니게임 참여여부 저장(참여날짜 저장)
        IsMiniGame isMiniGame= IsMiniGame.builder()
                .miniGame(miniGame)
                .member(member)
                .checkDate(checkDate)
                .build();

        isMiniGameRepository.save(isMiniGame);

        //랜덤으로 생성한 게임 반환
        return new MiniGameResponseDto(miniGame.getProblem(), miniGame.getAnswer());

    }

    // 문제와 답을 주면 맞는지 여부를 넘겨주고, 맞으면 경험치 UP
    @Transactional
    public boolean IsSuccess(MiniGameRequestDto miniGameRequestDto) {
        Member member = memberRepository.findById(miniGameRequestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("해당 멤버가 존재하지 않습니다 +id"+miniGameRequestDto.getMemberId()));

        String problem = miniGameRequestDto.getProblem();
        String answer = miniGameRequestDto.getAnswer();

        MiniGame miniGame = miniGameRepository.findByProblem(problem)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 문제입니다. problem="+problem));

        boolean isCorrect = miniGame.checkAnswer(answer);
        //맞는지 여부 체크후

        //대표캐릭터 경험치 올리기
        if (isCorrect) {
            UserCharacter mainChracter = member.getMainCharacter();
            if (mainChracter != null) {
                int currentExp = mainChracter.getExperience();
                mainChracter.updateExperience(currentExp + 5); //경험치 5씩 증가
            } else {
                throw new MainCharacterNotFoundException("대표캐릭터가 존재하지 않습니다. 멤버ID:" + member.getId());
            }
        }

        return isCorrect;
    }

}















