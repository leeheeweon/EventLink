package com.project.eventlink.reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record FindReservationListModel(
        LocalDate date,
        LocalTime time,
        String place,
        String memberId,
        String eventId
) {
}
