package com.project.eventlink.order.controller;

import com.project.eventlink.common.model.CommonResponse;
import com.project.eventlink.order.model.CreateOrderModel;
import com.project.eventlink.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public CommonResponse makeOrder(@RequestBody CreateOrderModel createOrderModel) {
        return CommonResponse.toResponse(HttpStatus.CREATED, orderService.makeOrder(createOrderModel));
    }
}

