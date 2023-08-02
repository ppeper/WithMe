package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.Attendance;
import com.bonobono.backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository  extends JpaRepository<Attendance,Long> {
    boolean existsByMemberIdAndCheckDate(Member member, LocalDate checkDate);

    List<Attendance> findAttendanceByMemberAndCheckDateBetween(Member member, LocalDate startDate, LocalDate endDate);
    //save사용 예정
}
