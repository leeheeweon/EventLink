package com.project.eventlink.reservation.service;

import com.project.eventlink.reservation.model.CreateReservationRequestModel;
import com.project.eventlink.reservation.model.DeleteReservationRequestModel;
import com.project.eventlink.reservation.model.FindReservationListModel;
import com.project.eventlink.reservation.model.FindReservationModel;
import com.project.eventlink.reservation.model.UpdateReservationRequestModel;

import java.util.List;

public interface ReservationService {

    List<FindReservationListModel> getReservationList();

    FindReservationModel getReservation(Long reservationId);

    Long add(CreateReservationRequestModel createReservationRequestModel);

    void delete(DeleteReservationRequestModel deleteReservationRequestModel);

    void update(UpdateReservationRequestModel updateReservationRequestModel);

}
