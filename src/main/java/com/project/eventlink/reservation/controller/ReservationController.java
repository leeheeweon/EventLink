package com.project.eventlink.reservation.controller;

import com.project.eventlink.common.model.CommonResponse;
import com.project.eventlink.reservation.model.CreateReservationRequestModel;
import com.project.eventlink.reservation.model.DeleteReservationRequestModel;
import com.project.eventlink.reservation.model.UpdateReservationRequestModel;
import com.project.eventlink.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/list")
    public CommonResponse reservationList() {
        return CommonResponse.toResponse(HttpStatus.OK, reservationService.getReservationList());
    }

    @GetMapping("/{reservationId}")
    public CommonResponse findReservation(@PathVariable Long reservationId) {
        return CommonResponse.toResponse(HttpStatus.OK, reservationService.getReservation(reservationId));
    }

    @PostMapping("/add")
    public CommonResponse add(CreateReservationRequestModel createReservationRequestModel) {
        return CommonResponse.toResponse(HttpStatus.OK, reservationService.add(createReservationRequestModel));
    }

    @DeleteMapping("/delete")
    public CommonResponse delete(DeleteReservationRequestModel deleteReservationRequestModel) {
        reservationService.delete(deleteReservationRequestModel);
        return CommonResponse.toResponse(HttpStatus.OK, deleteReservationRequestModel.reservationId());
    }

    @PutMapping("/update")
    public CommonResponse update(UpdateReservationRequestModel updateReservationRequestModel) {
        reservationService.update(updateReservationRequestModel);
        return CommonResponse.toResponse(HttpStatus.OK, updateReservationRequestModel.reservationId());
    }
}
