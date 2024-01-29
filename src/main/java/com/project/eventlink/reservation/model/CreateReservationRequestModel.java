package com.project.eventlink.reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationRequestModel(
        LocalDate date,
        LocalTime time,
        String place,
        Long eventId,
        String memberId
) {
}
