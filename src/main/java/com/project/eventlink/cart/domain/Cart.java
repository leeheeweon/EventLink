package com.project.eventlink.cart.domain;

import com.project.eventlink.entity.BasicEntity;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CART")
public class Cart extends BasicEntity {
    @Id
    @Column(name = "CART_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "cart")
    private final List<CartItem> cartItems = new ArrayList<>();

    public long totalPrice() {
        long totalPrice = 0;

        for (CartItem cartItem : cartItems) {
            Item item = cartItem.getItem();
            totalPrice += (long) item.getPrice() * cartItem.getCount();
        }

        return totalPrice;
    }
}
