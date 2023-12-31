package com.project.eventlink.cart.domain;

import com.project.eventlink.entity.BasicEntity;
import com.project.eventlink.item.domain.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CART_ITEM")
public class CartItem extends BasicEntity {
    @Id
    @Column(name = "CART_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public void addCount(int count) {
        this.count += count;
    }

    public long price() {
        return (long) item.getPrice() * count;
    }

}
