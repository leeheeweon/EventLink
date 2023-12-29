package com.project.eventlink.cart.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.member.dto.JoinForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest extends BaseSpringBootTest {
    @Autowired
    CartService cartService;

    @ParameterizedTest
    @ValueSource(strings = {"new_cart"})
    @DisplayName("장바구니 저장")
    void addCart() {
        //Given

        //When

        //Then
    }

}