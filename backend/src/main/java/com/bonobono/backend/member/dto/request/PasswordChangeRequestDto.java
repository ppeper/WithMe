package com.bonobono.backend.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordChangeRequestDto {

    String password;
    String newPassword;

}
