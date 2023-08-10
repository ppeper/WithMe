package com.bonobono.backend.location.entity;

import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private Long id;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id", nullable = false)
    private Location location;

    @Builder
    public Reward(Member member, Location location){
        this.member = member;
        this.location = location;
    }

    // 리워드 횟수 증가
    public void updateRewardCnt(){
        this.count++;
    }

}
