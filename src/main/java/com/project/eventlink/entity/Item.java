package com.project.eventlink.entity;

import com.project.eventlink.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ITEM")
public class Item extends BasicEntity{
    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "STOCK_QUANTITY")
    private Integer stockQuantity;
    @Column(name = "DETAIL")
    private String detail;
    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Image> images = new ArrayList<>();
}
