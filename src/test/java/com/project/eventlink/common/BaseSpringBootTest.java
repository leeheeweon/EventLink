package com.project.eventlink.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.entity.Role;
import com.project.eventlink.member.dto.JoinForm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class BaseSpringBootTest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MockMvc mockMvc;

    static protected Member testMember;

    @BeforeEach
    void init() {
        JoinForm joinForm = new JoinForm();
        joinForm.setUserId("before_test_user");
        joinForm.setPassword("before_test_password");
        joinForm.setRole(Role.USER);
        testMember = Member.createUserByJoinForm(joinForm);

        Authentication authentication = new TestingAuthenticationToken(testMember, "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
