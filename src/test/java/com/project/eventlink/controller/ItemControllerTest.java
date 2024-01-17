package com.project.eventlink.controller;

import com.project.eventlink.common.BaseSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends BaseSpringBootTest {

    @Test
    void getItemList() throws Exception {
        mockMvc.perform(get("/item/list?keyword=")
                        .with(user(testMember))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("item_list",
                        queryParameters(
                                parameterWithName("keyword").description("키워드")
                        ),
                        responseFields(
                                fieldWithPath("statusCode").description("Status code"),
                                fieldWithPath("status").description("Status message"),
                                fieldWithPath("data[].itemId").description("아이템 아이디"),
                                fieldWithPath("data[].name").description("아이템명"),
                                fieldWithPath("data[].price").description("가격"),
                                fieldWithPath("data[].stockQuantity").description("재고"),
                                fieldWithPath("data[].detail").description("상세내용")
                        )));
    }
}
