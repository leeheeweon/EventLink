package com.project.eventlink.controller;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.entity.Member;
import com.project.eventlink.entity.Role;
import com.project.eventlink.member.dto.JoinForm;
import com.project.eventlink.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class MemberControllerTest extends BaseSpringBootTest {

    @MockBean
    private MemberService memberService;


    @Test
    void findMemberById() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andDo(document("member-login"))
                .andExpect(view().name("views/login"))
                .andExpect(status().isOk());

        //TODO request & response 수정
    }

    @Test
    void home() throws Exception {
        //Given

        //When

        //Then
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/home"));
    }

    @Test
    void joinSelect() throws Exception {
        //Given

        //When

        //Then
        mockMvc.perform(get("/joinSelect"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/joinSelect"));
    }

    @Test
    void joinUserForm() throws Exception {
        //Given

        //When

        //Then
        mockMvc.perform(get("/joinUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/joinUser"));
    }

    @Test
    void joinProviderForm() throws Exception {
        //Given

        //When

        //Then
        mockMvc.perform(get("/joinProvider"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/joinProvider"));
    }

    @Test
    void joinUser() throws Exception {
        //Given
        JoinForm joinForm = new JoinForm();
        joinForm.setUserId("lee");
        joinForm.setRole(Role.USER);
        joinForm.setPassword("123");

        //When

        //Then
        mockMvc.perform(post("/joinUser")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(joinForm))
                        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void joinProvider() throws Exception {
        //Given
        JoinForm joinForm = new JoinForm();
        joinForm.setUserId("lee");
        joinForm.setRole(Role.PROVIDER);
        joinForm.setPassword("123");

        //When

        //Then
        mockMvc.perform(post("/joinProvider")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(joinForm))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void joinSuccessForm() throws Exception {
        //Given

        //When

        //Then
        mockMvc.perform(get("/joinSuccess")
                        .param("userId","test"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/joinSuccess"))
                .andExpect(model().attributeExists("userId"))
                .andDo(print());
    }

    @Test
    void loginPage() throws Exception {
        //Given

        //When

        //Then
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/login"));
    }
}
