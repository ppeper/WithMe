package com.bonobono.backend.dailymission.service;


import com.bonobono.backend.dailymission.domain.Attendance;
import com.bonobono.backend.dailymission.domain.IsMiniGame;
import com.bonobono.backend.dailymission.domain.OXQuiz;
import com.bonobono.backend.dailymission.domain.Quiz;
import com.bonobono.backend.dailymission.dto.TotalDailyMissionResponseDto;
import com.bonobono.backend.dailymission.repository.AttendanceRepository;
import com.bonobono.backend.dailymission.repository.IsMiniGameRepository;
import com.bonobono.backend.dailymission.repository.OXQuizRepository;
import com.bonobono.backend.dailymission.repository.QuizRepository;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalDailyMissionService {

    private final MemberRepository memberRepository;
    private final AttendanceRepository attendanceRepository;
    private final IsMiniGameRepository isMiniGameRepository;
    private final OXQuizRepository oxQuizRepository;
    private final QuizRepository quizRepository;

    LocalDate checkDate = LocalDate.now();


    int lastMonthDays= checkDate.getMonth().length(checkDate.isLeapYear()); //31일
    //1일 LocalDate.of = LocalDate 타입의 객체를 생성하는 메소드
    LocalDate startDate = LocalDate.of(checkDate.getYear(), checkDate.getMonth(),1);
    LocalDate endDate = LocalDate.of(checkDate.getYear(), checkDate.getMonth(), lastMonthDays);


    // date를 체크해서 한달 중 몇%를 했는지 반환해주는 함수
    @Transactional(readOnly = true)
    public TotalDailyMissionResponseDto AttendanceAndTotalPercentage() {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        List<Attendance> attendanceList= attendanceRepository.findAttendanceByMemberAndCheckDateBetween(member, startDate, endDate);
        int attendanceDays = attendanceList.size();

        int attendanceRate =  (int) (((double) attendanceDays / lastMonthDays) * 100);

        List<IsMiniGame> isMiniGameList = isMiniGameRepository.findIsMiniGameByMemberAndCheckDateBetween(member, startDate, endDate);
        List<OXQuiz> oxQuizList = oxQuizRepository.findOXQuizByMemberAndCheckDateBetween(member,startDate, endDate);
        List<Quiz> quizList = quizRepository.findQuizByMemberAndCheckDateBetween(member, startDate, endDate);

        int totalDays = isMiniGameList.size() + oxQuizList.size()+quizList.size();

        int totalRate = (int) (((double) totalDays / (lastMonthDays*3)) * 100);

        return new TotalDailyMissionResponseDto(attendanceRate, totalRate);

    }
}
