package com.project.eventlink.common;

import com.project.eventlink.user.dto.JoinForm;
import com.project.eventlink.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasicData {

    private final UserService userService;
    @PostConstruct
    public void init() {
        JoinForm test1 = new JoinForm();
        test1.setUserId("a");
        test1.setPassword("1");

        JoinForm test2 = new JoinForm();
        test2.setUserId("b");
        test2.setPassword("1");

        JoinForm test3 = new JoinForm();
        test3.setUserId("c");
        test3.setPassword("1");

        userService.save(test1);
        userService.save(test2);
        userService.save(test3);
    }
}
