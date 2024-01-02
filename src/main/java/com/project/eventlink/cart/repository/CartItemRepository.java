package com.project.eventlink.cart.repository;

import com.project.eventlink.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartCartIdAndItemItemId(Long cartId, Long itemId);
}
