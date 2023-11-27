package com.project.eventlink.entity;

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
@Table(name = "OPTION")
public class Option extends BasicEntity{
    @Id
    @Column(name = "OPTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;
    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @OneToMany(mappedBy = "option")
    private List<OptionDetail> optionDetails = new ArrayList<>();
}
