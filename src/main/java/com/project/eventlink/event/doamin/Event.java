package com.project.eventlink.event.doamin;

import com.project.eventlink.entity.BasicEntity;
import com.project.eventlink.entity.Image;
import com.project.eventlink.entity.ReservationEvent;
import com.project.eventlink.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "EVENT")
public class Event extends BasicEntity {
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
