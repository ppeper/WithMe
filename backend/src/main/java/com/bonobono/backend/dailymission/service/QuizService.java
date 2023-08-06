package com.bonobono.backend.dailymission.service;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.dailymission.domain.OXQuiz;
import com.bonobono.backend.dailymission.domain.Quiz;
import com.bonobono.backend.dailymission.dto.QuizRequestDto;
import com.bonobono.backend.dailymission.dto.QuizeResponseDto;
import com.bonobono.backend.dailymission.repository.OXQuizRepository;
import com.bonobono.backend.dailymission.repository.QuizRepository;
import com.bonobono.backend.global.exception.MainCharacterNotFoundException;
import com.bonobono.backend.member.entity.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {
    private final ChatgptService chatgptService;
    private final QuizRepository quizRepository;
    private final MemberRepository memberRepository;
    private final OXQuizRepository oxQuizRepository;

    Map<String, String> map = new HashMap<>();
    Map<String, String> oxmap = new HashMap<>();
    LocalDate checkDate = LocalDate.now();

    @Transactional
    public QuizeResponseDto checkfourQuiz(Long memberId) throws ParseException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당멤버가 존재하지 않습니다+id" + memberId));

        if (quizRepository.existsByMemberIdAndCheckDate(memberId, checkDate)) {
            log.trace("이미 게임에 참여했습니다");
            return new QuizeResponseDto(null, null, null, null);
        }

        //문제 생성
        String prompt = "어린이와 함께 해결할 수 있는 해양 환경오염 1 문제를 추천해줘. question, 4지선다(choices)와, 답변(answer), 해설(commentary)을 json형태로 만들어줘";
        String responseMessage = chatgptService.sendMessage(prompt);
        responseMessage.replaceAll("\n", "\\n").replace("+", "");
        System.out.println(responseMessage);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(responseMessage);

        JSONObject jsonObject = (JSONObject) obj;

        if (jsonObject.containsKey("question") && jsonObject.containsKey("choices") && jsonObject.containsKey("answer") && jsonObject.containsKey("commentary")) {
            map.put("question", String.valueOf(jsonObject.get("question")));
            map.put("choices", String.valueOf(jsonObject.get("choices")));
            map.put("answer", String.valueOf(jsonObject.get("answer")));
            map.put("commentary", String.valueOf(jsonObject.get("commentary")));
            QuizeResponseDto quizeResponseDto = new QuizeResponseDto(map.get("answer"), map.get("question"), map.get("choices"), map.get("commentary"));

            Quiz quiz = Quiz.builder()
                    .checkDate(checkDate)
                    .member(member)
                    .build();

            quizRepository.save(quiz);
            return quizeResponseDto;

        } else {
            throw new IllegalStateException("4지선다 퀴즈를 찾을 수 없습니다");
        }
    }

    //ox퀴즈
    @Transactional
    public QuizeResponseDto checkoxQuiz(Long memberId) throws ParseException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당멤버가 존재하지 않습니다+id" + memberId));

        if (oxQuizRepository.existsByMemberIdAndCheckDate(memberId, checkDate)) {
            log.trace("이미 게임에 참여했습니다");
            return new QuizeResponseDto(null, null, null, null);
        }

        String prompt = "어린이와 함께 해결할 수 있는 해양 환경오염 1 문제를 추천해줘. question, o/x 답변(answer), 해설(commentary)을 json형태로 만들어줘";
//        String responseMessage = chatgptService.sendMessage(prompt);
        String responseMessage = "{\n" +
                "\"question\": \"어린이들이 해변에서 낚시 후에 남은 작은 물고기를 바다로 돌려보내는 것은 문제가 될 수 있습니다. (O/X)\",\n" +
                "\"answer\": \"O\",\n" +
                "\"commentary\": \"많은 어린이들이 해변에서 낚시를 즐기는데, 작은 물고기들을 바다로 돌려보내는 것은 중요한 환경 보호 행동입니다. 작은 물고기들이 성장하여 해양 생태계를 유지하는데 도움을 주기 때문입니다. 어린이들은 작은 물고기를 돌려보내는 습관을 기르면서 바다의 균형을 지키는데 기여할 수 있습니다.\"\n" +
                "}";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(responseMessage);
        JSONObject jsonObject = (JSONObject) obj;

        if (jsonObject.containsKey("question") && jsonObject.containsKey("answer") && jsonObject.containsKey("commentary")) {
            oxmap.put("question", String.valueOf(jsonObject.get("question")));
            oxmap.put("answer", String.valueOf(jsonObject.get("answer")));
            oxmap.put("commentary", String.valueOf(jsonObject.get("commentary")));
            QuizeResponseDto quizeResponseDto = new QuizeResponseDto(map.get("answer"), map.get("question"), null, map.get("commentary"));

            OXQuiz quiz = OXQuiz.builder()
                    .checkDate(checkDate)
                    .member(member)
                    .build();

            oxQuizRepository.save(quiz);
            return quizeResponseDto;
        }
        else {
            throw new IllegalStateException("ox퀴즈를 찾을 수 없습니다");
        }
    }

    //맞는지 여부 체크
    @Transactional
    public boolean FourIsSuccess(QuizRequestDto quizRequestDto) {
        Member member = memberRepository.findById(quizRequestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("해당 멤버가 존재하지 않습니다 +id"+quizRequestDto.getMemberId()));

        String problem = quizRequestDto.getProblem();
        String answer = quizRequestDto.getAnswer();

        boolean isCorrect = false;
        if (map.get("problem").equals(problem) && map.get("answer").equals(answer)) {
            isCorrect=true;
        }

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

    @Transactional
    public boolean oxIsSuccess(QuizRequestDto quizRequestDto) {
        Member member = memberRepository.findById(quizRequestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("해당 멤버가 존재하지 않습니다 +id"+quizRequestDto.getMemberId()));

        String problem = quizRequestDto.getProblem();
        String answer = quizRequestDto.getAnswer();

        boolean isCorrect = false;
        if (oxmap.get("problem").equals(problem) && map.get("answer").equals(answer)) {
            isCorrect=true;
        }

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