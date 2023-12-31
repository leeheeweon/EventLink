package com.project.eventlink.cart.controller;

import com.project.eventlink.cart.service.CartService;
import com.project.eventlink.common.model.CommonResponse;
import com.project.eventlink.event.model.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    public CommonResponse eventList() {

//        return CommonResponse.toResponse(HttpStatus.OK, eventList);
        return CommonResponse.toResponse(HttpStatus.OK, null);
    }

}
