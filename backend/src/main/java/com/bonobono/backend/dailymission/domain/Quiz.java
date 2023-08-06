package com.bonobono.backend.dailymission.domain;

import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

//퀴즈 미션 참여여부
@Entity
@Table(name="quiz")
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate checkDate;


    @Builder
    public Quiz(Member member, LocalDate checkDate) {
        this.member=member;
        this.checkDate=checkDate;
    }

}
