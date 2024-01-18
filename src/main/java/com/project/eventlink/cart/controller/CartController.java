package com.project.eventlink.cart.controller;

import com.project.eventlink.cart.model.CreateCartRequestModel;
import com.project.eventlink.cart.service.CartService;
import com.project.eventlink.common.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/list")
    public CommonResponse cartList(String memberId) {
        return CommonResponse.toResponse(HttpStatus.OK, cartService.cartItemList(memberId));
    }

    @PostMapping("/add")
    public CommonResponse addCart(CreateCartRequestModel createCartRequestModel) {
        return CommonResponse.toResponse(HttpStatus.OK, cartService.addCart(createCartRequestModel));
    }

    @DeleteMapping("/delete")
    public CommonResponse deleteCartItem(Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return CommonResponse.toResponse(HttpStatus.OK, null);
    }
}
