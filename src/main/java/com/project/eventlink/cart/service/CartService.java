package com.project.eventlink.cart.service;

import com.project.eventlink.cart.domain.CartItem;
import com.project.eventlink.cart.model.CreateCartRequestModel;

import java.util.List;

public interface CartService {

    Long addCart(CreateCartRequestModel createCartRequestModel);

    List<CartItem> cartItemList(String memberId);

    void deleteCartItem(Long cartItemId);
}
