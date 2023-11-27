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
@Table(name = "EVENT")
public class Event extends BasicEntity{
    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MIN_PRICE")
    private Integer minPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "event")
    private List<ReservationEvent> reservationEvents = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<Image> images = new ArrayList<>();
}
