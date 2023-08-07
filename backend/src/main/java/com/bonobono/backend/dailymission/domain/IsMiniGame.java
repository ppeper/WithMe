package com.bonobono.backend.dailymission.domain;

import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class IsMiniGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate checkDate;

    @Builder
    public IsMiniGame(Member member, LocalDate checkDate) {
        this.member=member;
        this.checkDate=checkDate;
    }
}
