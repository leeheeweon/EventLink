package com.project.eventlink.controller;

import com.project.eventlink.common.BaseSpringBootTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:db/event.sql")
class EventControllerTest extends BaseSpringBootTest {

    @Test
    @DisplayName("이벤트 조회")
    void getEventList() throws Exception {
        mockMvc.perform(get("/event/list")
                        .with(user(testMember))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("event-list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("statusCode").description("Status code"),
                                fieldWithPath("status").description("Status message"),
                                fieldWithPath("data[]").description("Events data array"),
                                fieldWithPath("data[].name").description("Event name"),
                                fieldWithPath("data[].minPrice").description("Event minimum price"),
                                fieldWithPath("data[].memberId").description("Member ID")
                        )
                ))
                .andExpect(status().isOk());
    }
}
