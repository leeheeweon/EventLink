package com.project.eventlink.item.option.domain;

import com.project.eventlink.entity.BasicEntity;
import com.project.eventlink.item.domain.Item;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OPTION")
public class Option {
    @Id
    @Column(name = "OPTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;
    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Builder.Default
    @OneToMany(mappedBy = "option" ,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OptionDetail> optionDetails = new ArrayList<>();

    public void addOptionDetail(OptionDetail optionDetail) {
        optionDetails.add(optionDetail);
    }
}
