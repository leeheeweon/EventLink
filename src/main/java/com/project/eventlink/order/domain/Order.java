package com.project.eventlink.order.domain;

import com.project.eventlink.entity.BasicEntity;
import com.project.eventlink.entity.Delivery;
import com.project.eventlink.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDERS")
public class Order extends BasicEntity {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @Builder
    public Order(OrderStatus orderStatus, Member member, Delivery delivery) {
        this.orderStatus = orderStatus;
        this.member = member;
        this.delivery = delivery;
    }
}
