package com.bonobono.backend.chatting.repository;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByRoomNumber(int roomNumber);

    Optional<ChatRoom> findByMemberAndOther(Member member, Member other);
}
