package com.project.eventlink.reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateReservationRequestModel(
        Long reservationId,
        LocalDate date,
        LocalTime time,
        String place
) {
}
