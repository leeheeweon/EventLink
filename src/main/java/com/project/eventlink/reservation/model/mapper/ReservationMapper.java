package com.project.eventlink.reservation.model.mapper;

import com.project.eventlink.reservation.domain.Reservation;
import com.project.eventlink.reservation.model.FindReservationListModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "event.eventId", target = "eventId")
    FindReservationListModel toReservationResponseModel(Reservation reservation);

}
