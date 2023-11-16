package com.project.eventlink.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
