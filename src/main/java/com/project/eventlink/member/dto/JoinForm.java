package com.project.eventlink.member.dto;

import com.project.eventlink.entity.Role;
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

    private Role role;
}
