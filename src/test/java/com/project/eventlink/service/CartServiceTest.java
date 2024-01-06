package com.project.eventlink.service;

import com.project.eventlink.cart.domain.Cart;
import com.project.eventlink.cart.domain.CartItem;
import com.project.eventlink.cart.model.CreateCartRequestModel;
import com.project.eventlink.cart.repository.CartItemRepository;
import com.project.eventlink.cart.repository.CartRepository;
import com.project.eventlink.cart.service.CartService;
import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class CartServiceTest extends BaseSpringBootTest {
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    CartItemRepository cartItemRepository;

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
        Long addedItemId = itemService.addItem(new CreateItemRequestModel("name", 1000, 10, "detail"));
        CreateCartRequestModel cartItemForm = new CreateCartRequestModel("test", addedItemId, 1);

        //When
        Long cartId = cartService.addCart(cartItemForm);
        System.out.println("cartId = " + cartId);

        Cart cart = cartRepository.findById(cartId).get();
        //Then
        Assertions.assertThat(cart.getMember().getMemberId()).isEqualTo("test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"new_cart"})
    @DisplayName("장바구니 삭제")
    void deleteCartItem() {
        //Given
        Long addedItemId = itemService.addItem(new CreateItemRequestModel("name", 1000, 10, "detail"));
        CreateCartRequestModel cartItemForm = new CreateCartRequestModel("a", addedItemId, 1);

        //When
        Long cartId = cartService.addCart(cartItemForm);
        CartItem cartItem = cartItemRepository.findByCartCartIdAndItemItemId(cartId, addedItemId);
        cartService.deleteCartItem(cartItem.getCartItemId());

        //Then
        CartItem deletedCartItem = cartItemRepository.findByCartCartIdAndItemItemId(cartId, addedItemId);
        Assertions.assertThat(deletedCartItem).isNull();
    }
}