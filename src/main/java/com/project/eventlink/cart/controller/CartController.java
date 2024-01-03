package com.project.eventlink.cart.controller;

import com.project.eventlink.cart.service.CartServiceImpl;
import com.project.eventlink.common.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartServiceImpl;

    @GetMapping("/cart")
    public CommonResponse cartList(String memberId) {
        return CommonResponse.toResponse(HttpStatus.OK, cartServiceImpl.cartItemList(memberId));
    }

}
