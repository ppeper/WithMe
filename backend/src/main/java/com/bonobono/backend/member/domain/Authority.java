package com.bonobono.backend.member.domain;

import com.bonobono.backend.member.domain.enumtype.Role;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getRole() {

        return this.role.toString();
    }

    @Builder
    public Authority(Role role) {

        this.role = role;
    }
}