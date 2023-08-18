package com.bonobono.backend.dailymission.service;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.service.UpgradeCharacterLevelService;
import com.bonobono.backend.dailymission.domain.OXQuiz;
import com.bonobono.backend.dailymission.domain.OXQuizProblem;
import com.bonobono.backend.dailymission.domain.Quiz;
import com.bonobono.backend.dailymission.domain.QuizProblem;
import com.bonobono.backend.dailymission.dto.QuizRequestDto;
import com.bonobono.backend.dailymission.dto.QuizeResponseDto;
import com.bonobono.backend.dailymission.exception.AlreadyParticipatedException;
import com.bonobono.backend.dailymission.repository.OXQuizProblemRepository;
import com.bonobono.backend.dailymission.repository.OXQuizRepository;
import com.bonobono.backend.dailymission.repository.QuizProblemRepository;
import com.bonobono.backend.dailymission.repository.QuizRepository;
import com.bonobono.backend.global.exception.MainCharacterNotFoundException;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {
    private final QuizRepository quizRepository;
    private final MemberRepository memberRepository;
    private final OXQuizRepository oxQuizRepository;
    private final QuizProblemRepository quizProblemRepository;
    private final OXQuizProblemRepository oxQuizProblemRepository;
    private final UpgradeCharacterLevelService upgradeCharacterLevelService;

    LocalDate checkDate = LocalDate.now();

    @Transactional
    public QuizeResponseDto checkfourQuiz(Long memberId) throws ParseException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당멤버가 존재하지 않습니다+id" + memberId));

//        if (quizRepository.existsByMemberIdAndCheckDate(memberId, checkDate)) {
//            log.trace("이미 게임에 참여했습니다");
//            throw new AlreadyParticipatedException("이미 게임에 참여했습니다");
//        }

        long qty = quizProblemRepository.findAll().size();
        int idx = (int) (Math.random()*qty);

        Page<QuizProblem> quizPage = quizProblemRepository.findAll(PageRequest.of(idx,1, Sort.by("id")));
        QuizProblem quizProblem = null;

        if(quizPage.hasContent()) {
            quizProblem=quizPage.getContent().get(0);
        } else {
            throw new IllegalStateException("4지선다 퀴즈를 찾을 수 없습니다");
        }

        return new QuizeResponseDto(quizProblem.getAnswer(), quizProblem.getProblem(), quizProblem.getId(), quizProblem.getChoices(), quizProblem.getCommentary());

    }

    //ox퀴즈
    @Transactional
    public QuizeResponseDto checkoxQuiz(String type) throws ParseException {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        if (type.equals("quiz")) {
//        if (oxQuizRepository.existsByMemberIdAndCheckDate(memberId, checkDate)) {
//            log.trace("이미 게임에 참여했습니다");
//            throw new AlreadyParticipatedException("이미 게임에 참여했습니다");
//        }
        }

        long qty = oxQuizProblemRepository.findAll().size();
        int idx = (int) (Math.random()*qty);

        Page<OXQuizProblem> oxquizPage = oxQuizProblemRepository.findAll(PageRequest.of(idx,1, Sort.by("id")));
        OXQuizProblem oxQuizProblem = null;

        if(oxquizPage.hasContent()) {
            oxQuizProblem=oxquizPage.getContent().get(0);
        } else {
            throw new IllegalStateException("ox퀴즈를 찾을 수 없습니다");
        }


        return new QuizeResponseDto(oxQuizProblem.getAnswer(), oxQuizProblem.getProblem(), oxQuizProblem.getId(), null,oxQuizProblem.getCommentary());

    }

    //맞는지 여부 체크
    @Transactional
    public boolean FourIsSuccess(QuizRequestDto quizRequestDto) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        String answer = quizRequestDto.getAnswer();
        Long problemId = quizRequestDto.getProblemId();

        QuizProblem quizProblem = quizProblemRepository.findById(problemId)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 문제입니다 problem="+problemId));

        boolean isCorrect = quizProblem.checkAnswer(answer);

        if (isCorrect) {
            UserCharacter mainChracter = member.getMainCharacter();
            if (mainChracter != null) {
                int currentExp = mainChracter.getExperience();
                mainChracter.updateExperience(currentExp + 5); //경험치 5씩 증가
                upgradeCharacterLevelService.upgradeCharacter(mainChracter,mainChracter.getExperience()); //레벨업 체크

            } else {
                throw new MainCharacterNotFoundException("대표캐릭터가 존재하지 않습니다. 멤버ID:" + member.getId());
            }
        }

        Quiz quiz = Quiz.builder()
                .checkDate(checkDate)
                .member(member)
                .build();

        quizRepository.save(quiz);

        return isCorrect;
    }

    @Transactional
    public boolean oxIsSuccess(String type, QuizRequestDto quizRequestDto) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        String answer = quizRequestDto.getAnswer();
        Long problemId = quizRequestDto.getProblemId();

        OXQuizProblem oxQuizProblem = oxQuizProblemRepository.findById(problemId)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 문제입니다 problem="+problemId));

        boolean isCorrect = oxQuizProblem.checkAnswer(answer);

        if (isCorrect) {
            UserCharacter mainChracter = member.getMainCharacter();
            if (mainChracter != null) {
                int currentExp = mainChracter.getExperience();
                if (type.equals("map")) {
                    mainChracter.updateExperience(currentExp + 20); //경험치 20씩 증가
                }
                else {
                    mainChracter.updateExperience(currentExp + 5); //경험치 5씩 증가
                }
                upgradeCharacterLevelService.upgradeCharacter(mainChracter,mainChracter.getExperience());
            } else {
                throw new MainCharacterNotFoundException("대표캐릭터가 존재하지 않습니다. 멤버ID:" + member.getId());
            }
        }

        OXQuiz oxQuiz = OXQuiz.builder()
                .checkDate(checkDate)
                .member(member)
                .build();

        oxQuizRepository.save(oxQuiz);

        return isCorrect;
    }


}