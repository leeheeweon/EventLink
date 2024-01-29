package com.project.eventlink.reservation.service;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.repository.EventRepository;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.member.repository.MemberRepository;
import com.project.eventlink.reservation.domain.Reservation;
import com.project.eventlink.reservation.model.CreateReservationRequestModel;
import com.project.eventlink.reservation.model.DeleteReservationRequestModel;
import com.project.eventlink.reservation.model.FindReservationListModel;
import com.project.eventlink.reservation.model.FindReservationModel;
import com.project.eventlink.reservation.model.UpdateReservationRequestModel;
import com.project.eventlink.reservation.model.mapper.ReservationMapper;
import com.project.eventlink.reservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    @Override
    public List<FindReservationListModel> getReservationList() {
        List<Reservation> list = reservationRepository.findAll();
        return list.stream().map(reservationMapper::toReservationResponseModel).toList();
    }

    @Override
    public FindReservationModel getReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findByReservationId(reservationId);
        return new FindReservationModel(reservation.getDate(), reservation.getTime(), reservation.getPlace());
    }

    @Override
    public Long add(CreateReservationRequestModel createReservationRequestModel) {
        Member member = memberRepository.findByMemberId(createReservationRequestModel.memberId());
        Event event = eventRepository.findByEventId(createReservationRequestModel.eventId());
        Reservation reservation = Reservation.builder()
                .date(createReservationRequestModel.date())
                .time(createReservationRequestModel.time())
                .place(createReservationRequestModel.place())
                .member(member)
                .event(event)
                .build();
        return reservationRepository.save(reservation).getReservationId();
    }

    @Override
    public void delete(DeleteReservationRequestModel deleteReservationRequestModel) {
        reservationRepository.deleteById(deleteReservationRequestModel.reservationId());
    }

    @Override
    public void update(UpdateReservationRequestModel updateReservationRequestModel) {
        Reservation reservation = reservationRepository.findByReservationId(updateReservationRequestModel.reservationId());
        reservation.update(updateReservationRequestModel);
    }
}
