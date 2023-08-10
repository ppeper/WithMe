package com.bonobono.backend.location.repository;

import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.location.entity.Reward;
import com.bonobono.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {

    List<Reward> findAllByLocation(Location location);

    Optional<Reward> findByMemberAndLocation(Member member, Location location);


    @Modifying
    @Query("update Reward r set r.count = r.count + 1 where r.id = :rewardId ")
    void updateRewardCnt(@Param("rewardId") Long rewardId);
}
