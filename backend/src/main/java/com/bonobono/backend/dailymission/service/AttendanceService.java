package com.bonobono.backend.dailymission.service;

import com.bonobono.backend.dailymission.domain.Attendance;
import com.bonobono.backend.dailymission.dto.AttendanceDto;
import com.bonobono.backend.dailymission.repository.AttendanceRepository;
import com.bonobono.backend.member.entity.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceService {
    //들어온 시간(request dto)가 누른 최근시간과 같은지 비교해서 오류를 반환한다.
    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;
    LocalDate checkDate = LocalDate.now();

    @Transactional
    public boolean check(AttendanceDto attendanceDto) {
        Member member = memberRepository.findById(attendanceDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("해당 멤버가 존재하지 않습니다 +id"+attendanceDto.getMemberId()));
        if (attendanceRepository.existsByMemberIdAndCheckDate(member, checkDate)) {
            log.trace("이미 출석했습니다");
            return false;
        }

        Attendance attendance = Attendance.builder()
                .checkDate(checkDate)
                        .member(member)
                        .build();

        attendanceRepository.save(attendance);
        return true;
    }

    // date를 체크해서 한달 중 몇%를 했는지 반환해주는 함수
    @Transactional(readOnly = true)
    public int AttendancePercentage(AttendanceDto attendanceDto) {
        Member member = memberRepository.findById(attendanceDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("해당 멤버가 존재하지 않습니다 +id"+attendanceDto.getMemberId()));

        int lastMonthDays= checkDate.getMonth().length(checkDate.isLeapYear()); //31일
        //1일 LocalDate.of = LocalDate 타입의 객체를 생성하는 메소드
        LocalDate startDate = LocalDate.of(checkDate.getYear(), checkDate.getMonth(),1);
        LocalDate endDate = LocalDate.of(checkDate.getYear(), checkDate.getMonth(), lastMonthDays);
        List<Attendance> attendanceList= attendanceRepository.findAttendanceByMemberAndCheckDateBetween(member, startDate, endDate);
        int attendanceDays = attendanceList.size();

        return (int) (((double) attendanceDays / lastMonthDays) * 100);
    }


}