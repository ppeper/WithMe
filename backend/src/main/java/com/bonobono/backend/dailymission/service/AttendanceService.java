package com.bonobono.backend.dailymission.service;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.service.UpgradeCharacterLevelService;
import com.bonobono.backend.dailymission.domain.Attendance;
import com.bonobono.backend.dailymission.repository.AttendanceRepository;
import com.bonobono.backend.global.exception.MainCharacterNotFoundException;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceService {
    //들어온 시간(request dto)가 누른 최근시간과 같은지 비교해서 오류를 반환한다.
    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;
    private final UpgradeCharacterLevelService upgradeCharacterLevelService;
    LocalDate checkDate = LocalDate.now();

    @Transactional
    public boolean check(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("해당 멤버가 존재하지 않습니다 +id"+memberId));
//        if (attendanceRepository.existsByMemberIdAndCheckDate(memberId, checkDate)) {
//            log.trace("이미 출석했습니다");
//            return false;
//        }
//        else {
            Attendance attendance = Attendance.builder()
                    .checkDate(checkDate)
                    .member(member)
                    .build();

            //대표캐릭터 경험치 올리기
            UserCharacter mainChracter = member.getMainCharacter();
            if (mainChracter != null) {
                int currentExp = mainChracter.getExperience();
                mainChracter.updateExperience(currentExp + 5); //경험치 5씩 증가
                upgradeCharacterLevelService.upgradeCharacter(mainChracter,mainChracter.getExperience());
            } else {
                throw new MainCharacterNotFoundException("대표캐릭터가 존재하지 않습니다. 멤버ID:" + member.getId());
            }
            attendanceRepository.save(attendance);
            return true;
//        }
    }




}