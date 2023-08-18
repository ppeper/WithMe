package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileImgRepository extends JpaRepository<ProfileImg, Long> {

    Optional<ProfileImg> findByMember(Member member);
}