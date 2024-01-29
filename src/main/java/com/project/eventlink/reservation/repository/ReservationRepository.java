package com.project.eventlink.reservation.repository;

import com.project.eventlink.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByReservationId(Long reservationId);
}
