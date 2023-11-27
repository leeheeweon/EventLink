package com.project.eventlink.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OPTION_DETAIL")
public class OptionDetail extends BasicEntity{
    @Id
    @Column(name = "OPTION_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionDetailId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTION_ID")
    private Option option;
}
