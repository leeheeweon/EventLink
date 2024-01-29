package com.project.eventlink.reservation.domain;

import com.project.eventlink.entity.BasicEntity;
import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.reservation.model.UpdateReservationRequestModel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BasicEntity {
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "TIME")
    private LocalTime time;
    @Column(name = "PLACE")
    private String place;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void update(UpdateReservationRequestModel updateReservationRequestModel) {
        this.date = updateReservationRequestModel.date();
        this.time = updateReservationRequestModel.time();
        this.place = updateReservationRequestModel.place();
    }
}
