package com.project.eventlink.member.dto;

import com.project.eventlink.entity.Address;
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

    private Integer age;

    private String name;

    private Address address;

    private String phone;

    private String email;

    private Role role;
}
