package com.project.eventlink.common;

import com.project.eventlink.entity.Role;
import com.project.eventlink.member.dto.JoinForm;
import com.project.eventlink.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasicData {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        JoinForm test1 = new JoinForm();
        test1.setUserId("a");
        test1.setPassword("1");
        test1.setRole(Role.USER);

        JoinForm test2 = new JoinForm();
        test2.setUserId("b");
        test2.setPassword("1");
        test2.setRole(Role.PROVIDER);

        memberService.save(test1);
        memberService.save(test2);
    }
}
