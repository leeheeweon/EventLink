package com.project.eventlink.cart.service;

import com.project.eventlink.cart.domain.Cart;
import com.project.eventlink.cart.model.CreateCartRequestModel;
import com.project.eventlink.cart.repository.CartRepository;
import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

class CartServiceImplTest extends BaseSpringBootTest {
    @Autowired
    CartServiceImpl cartServiceImpl;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemService itemService;

    @ParameterizedTest
    @ValueSource(strings = {"new_cart"})
    @DisplayName("장바구니 저장")
    void addCart() {
        /**
         * TODO
         * 1. memberId a 사용
         * 2. item 저장
         * 3. item 장바구니 저장
         */

        //Given
        String memberId = "test";
        Long addedItemId = itemService.addItem(new CreateItemRequestModel("name", 1000, 10, "detail"));
        CreateCartRequestModel cartItemForm = new CreateCartRequestModel(addedItemId, 1);

        //When
        Long cartId = cartServiceImpl.addCart(cartItemForm, memberId);
        System.out.println("cartId = " + cartId);

        Cart cart = cartRepository.findById(cartId).get();
        //Then
        Assertions.assertThat(cart.getMember().getMemberId()).isEqualTo(memberId);
    }
}