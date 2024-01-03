package com.project.eventlink.entity;

import com.project.eventlink.event.doamin.Event;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationEvent extends BasicEntity {

    @Id
    @Column(name = "RESERVATION_EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;


}
