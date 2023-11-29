package com.project.eventlink.controller;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.entity.Member;
import com.project.eventlink.entity.Role;
import com.project.eventlink.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends BaseSpringBootTest {

    @MockBean
    private MemberService memberService;

    @Test
    void findMemberById() throws Exception {
        //given
        given(memberService.loadUserByUsername(any()))
                .willReturn(new Member("id", "123", Role.USER));

        //when
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andDo(document("member-login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk()); //then

        //TODO request & response 수정
    }
}
