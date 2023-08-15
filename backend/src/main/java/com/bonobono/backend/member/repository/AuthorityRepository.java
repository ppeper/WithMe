package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.enumtype.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Role> {

    Optional<Authority> findByRole(Role role);
}