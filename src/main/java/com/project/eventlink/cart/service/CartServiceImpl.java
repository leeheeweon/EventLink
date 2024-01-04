package com.project.eventlink.cart.service;

import com.project.eventlink.cart.domain.Cart;
import com.project.eventlink.cart.domain.CartItem;
import com.project.eventlink.cart.model.CreateCartRequestModel;
import com.project.eventlink.cart.repository.CartItemRepository;
import com.project.eventlink.cart.repository.CartRepository;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.repository.ItemRepository;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long addCart(CreateCartRequestModel createCartRequestModel, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        Cart cart = cartRepository.findByMemberMemberId(memberId);
        Item item = itemRepository.findByItemId(createCartRequestModel.itemId());

        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartCartIdAndItemItemId(cart.getCartId(), item.getItemId());

        if (savedCartItem != null) {
            savedCartItem.addCount(createCartRequestModel.count());
            return savedCartItem.getCartItemId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, createCartRequestModel.count());
            cartItemRepository.save(cartItem);
            return cartItem.getCartItemId();
        }
    }

    public List<CartItem> cartItemList(String memberId) {
        Cart cart = cartRepository.findByMemberMemberId(memberId);
        return cart.getCartItems();
    }

    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
