package com.bonobono.backend.dailymission.domain;

import com.bonobono.backend.member.entity.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "minigame_id")
    private MiniGame miniGame;

    private LocalDate checkDate;

    @Builder
    public IsMiniGame(Member member, MiniGame miniGame, LocalDate checkDate) {
        this.member=member;
        this.miniGame=miniGame;
        this.checkDate=checkDate;
    }
}
