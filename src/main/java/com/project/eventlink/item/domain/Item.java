package com.project.eventlink.item.domain;

import com.project.eventlink.cart.domain.CartItem;
import com.project.eventlink.entity.*;
import com.project.eventlink.item.model.UpdateItemRequestModel;
import com.project.eventlink.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ITEM")
public class Item extends BasicEntity {
    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;
    @Column(name = "DETAIL")
    private String detail;
    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "item" )
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Image> images = new ArrayList<>();

    public void updateItem(UpdateItemRequestModel updateItemRequestModel) {
        this.name = updateItemRequestModel.name();
        this.price = updateItemRequestModel.price();
        this.stockQuantity = updateItemRequestModel.quantity();
        this.detail = updateItemRequestModel.detail();
        this.sellStatus = SellStatus.valueOf(updateItemRequestModel.sellStatus().toUpperCase());
    }
}
