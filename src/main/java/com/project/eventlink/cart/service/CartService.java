package com.project.eventlink.cart.service;

import com.project.eventlink.cart.model.CreateCartRequestModel;

public interface CartService {
    Long addCart(CreateCartRequestModel createCartRequestModel, String memberId);
}
