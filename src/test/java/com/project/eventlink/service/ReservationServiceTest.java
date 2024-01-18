package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.reservation.model.CreateReservationRequestModel;
import com.project.eventlink.reservation.model.DeleteReservationRequestModel;
import com.project.eventlink.reservation.model.FindReservationListModel;
import com.project.eventlink.reservation.model.FindReservationModel;
import com.project.eventlink.reservation.model.UpdateReservationRequestModel;
import com.project.eventlink.reservation.service.ReservationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class ReservationServiceTest extends BaseSpringBootTest {

    @Autowired
    ReservationService reservationService;

    @Test
    @DisplayName("예약 리스트 가져오기")
    void getReservationList() {
        //Given
        CreateReservationRequestModel createReservationRequestModel = new CreateReservationRequestModel(LocalDate.now(), LocalTime.now(), "동탄", 0L, "test");
        reservationService.add(createReservationRequestModel);
        //When
        List<FindReservationListModel> reservationList = reservationService.getReservationList();

        //Then
        assertThat(reservationList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("예약 상세 가져오기")
    void getReservation() {
        //Given
        CreateReservationRequestModel createReservationRequestModel = new CreateReservationRequestModel(LocalDate.now(), LocalTime.now(), "동탄", 0L, "test");
        Long addedReservationId = reservationService.add(createReservationRequestModel);
        //When
        FindReservationModel reservation = reservationService.getReservation(addedReservationId);
        //Then
        assertThat(reservation.place()).isEqualTo(createReservationRequestModel.place());
    }

    @Test
    @DisplayName("예약 추가")
    void add() {
        //Given
        CreateReservationRequestModel createReservationRequestModel = new CreateReservationRequestModel(LocalDate.now(), LocalTime.now(), "동탄", 0L, "test");
        //When
        Long addedReservationId = reservationService.add(createReservationRequestModel);
        FindReservationModel reservation = reservationService.getReservation(addedReservationId);

        //Then
        assertThat(reservation).isNotNull();
    }

    @Test
    @DisplayName("예약 삭제")
    void delete() {
        //Given
        CreateReservationRequestModel createReservationRequestModel = new CreateReservationRequestModel(LocalDate.now(), LocalTime.now(), "동탄", 0L, "test");
        Long addedReservationId = reservationService.add(createReservationRequestModel);
        //When
        reservationService.delete(new DeleteReservationRequestModel(addedReservationId));
        List<FindReservationListModel> reservationList = reservationService.getReservationList();
        //Then
        assertThat(reservationList.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("예약 수정")
    void update() {
        //Given
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        CreateReservationRequestModel createReservationRequestModel = new CreateReservationRequestModel(dateNow, timeNow, "동탄", 0L, "test");
        Long addedReservationId = reservationService.add(createReservationRequestModel);
        //When
        UpdateReservationRequestModel updateReservationRequestModel = new UpdateReservationRequestModel(addedReservationId, dateNow.plusDays(1), timeNow.plusHours(1), "용인");
        reservationService.update(updateReservationRequestModel);
        FindReservationModel reservation = reservationService.getReservation(addedReservationId);
        //Then
        assertThat(reservation.place()).isEqualTo(updateReservationRequestModel.place());
    }
}