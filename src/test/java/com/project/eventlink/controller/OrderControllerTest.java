package com.project.eventlink.controller;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.order.model.CreateOrderModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends BaseSpringBootTest {

    @Test
    @DisplayName("주문 생성")
    void createOrder() throws Exception {
        //given
        CreateOrderModel createOrderModel = new CreateOrderModel(1L, 1, "city", "street", "zipcode");
        //when
        //then
        mockMvc.perform(post("/order")
                        .with(csrf())
                        .with(user(testMember))
                        .content(objectMapper.writeValueAsString(createOrderModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("order-create",
                        requestFields(
                                fieldWithPath("itemId").description("Item ID"),
                                fieldWithPath("count").description("Order count"),
                                fieldWithPath("city").description("Delivery city"),
                                fieldWithPath("street").description("Delivery street"),
                                fieldWithPath("zipcode").description("Delivery zipcode")
                        ),
                        responseFields(
                                fieldWithPath("statusCode").description("Status code"),
                                fieldWithPath("status").description("Status message"),
                                fieldWithPath("data").description("Order ID")
                        )));
    }
}
