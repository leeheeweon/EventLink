package com.project.eventlink.event.service;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.CreateEventRequestModel;
import com.project.eventlink.event.model.DeleteEventRequestModel;
import com.project.eventlink.event.model.FindEventListModel;
import com.project.eventlink.event.model.FindEventModel;
import com.project.eventlink.event.model.UpdateEventRequestModel;
import com.project.eventlink.event.model.mapper.EventMapper;
import com.project.eventlink.event.repository.EventRepository;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final MemberRepository memberRepository;

    @Override
    public List<FindEventListModel> getEventList() {

        List<Event> eventList = eventRepository.findAll();
        return eventList.stream().map(eventMapper::toEventResponseModel).toList();
    }

    @Override
    public Long addEvent(CreateEventRequestModel createEventRequestModel) {
        //TODO : memberId를 세션에서 가져온다고 가정
        Member member = memberRepository.findByMemberId(createEventRequestModel.memberId());
        Event event = Event.builder().name(createEventRequestModel.name()).minPrice(createEventRequestModel.minPrice()).member(member).build();

        return eventRepository.save(event).getEventId();
    }

    @Override
    public FindEventModel getEvent(Long eventId) {
        Event event = eventRepository.findByEventId(eventId);
        return new FindEventModel(event.getName(), event.getMinPrice(), event.getMember().getMemberId());
    }

    @Override
    public void deleteEvent(DeleteEventRequestModel deleteEventRequestModel) {
        eventRepository.deleteById(deleteEventRequestModel.eventId());
    }

    @Override
    public void updateEvent(UpdateEventRequestModel updateEventRequestModel) {
        Event event = eventRepository.findByEventId(updateEventRequestModel.eventId());
        event.updateEvent(updateEventRequestModel);
    }
}
