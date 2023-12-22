package com.project.eventlink.cart.service;

import com.project.eventlink.cart.domain.Cart;
import com.project.eventlink.cart.domain.CartItem;
import com.project.eventlink.cart.dto.CartItemForm;
import com.project.eventlink.cart.repository.CartItemRepository;
import com.project.eventlink.cart.repository.CartRepository;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;

    public Long addCart(CartItemForm cartItemForm, String memberId) {
        //TODO
        // 1. 매개변수 세팅 ( CartForm, memberId)[X]
        // 2. 아이템 아이디, 수량을 속성으로 가지는 CartForm 만들기[X]
        // 3. 아이템 아이디로 아이템 찾아오기
        // 4. memberId로 멤버 찾아오기[X]
        // 5. memberId로 cart 찾아오기(1:1매핑)[X]
        // 6. cart 생성시점 고려필요(회원가입시 or 카트에 추가할때)[X]
        // 7. 카트 아이디와, 아이템 아이디로 저장된 카트아이템 가져오기
        // 8. 카트아이템이 있으면 addCount 호출
        // 9. 카트아이템이 없으면 save

        /*Member member = memberRepository.findByMemberId(memberId);
        Cart cart = cartRepository.findByMemberId(memberId);

        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getCartId(), item.getId);

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemForm.getCount());
            return savedCartItem.getCartItemId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemForm.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getCartItemId();
        }
         */
        return 0L;
    }
}
