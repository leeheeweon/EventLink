package com.project.eventlink.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotBlank
    private String memberId;
    @NotBlank
    private String password;
}
