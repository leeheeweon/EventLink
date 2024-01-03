package com.project.eventlink.cart.service;

import com.project.eventlink.cart.domain.CartItem;
import com.project.eventlink.cart.model.CreateCartRequestModel;

import java.util.List;

public interface CartService {

    Long addCart(CreateCartRequestModel cartItemForm, String memberId);

    List<CartItem> cartItemList(String memberId);
}
