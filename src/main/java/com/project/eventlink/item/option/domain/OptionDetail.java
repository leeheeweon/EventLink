package com.project.eventlink.item.option.domain;

import com.project.eventlink.entity.BasicEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OPTION_DETAIL")
public class OptionDetail {
    @Id
    @Column(name = "OPTION_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionDetailId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTION_ID")
    private Option option;
}
