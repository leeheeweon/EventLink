package com.project.eventlink.cart.repository;

import com.project.eventlink.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberMemberId(String  memberId);
}
