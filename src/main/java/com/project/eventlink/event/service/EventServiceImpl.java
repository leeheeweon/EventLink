package com.project.eventlink.event.service;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.EventResponse;
import com.project.eventlink.event.model.mapper.EventMapper;
import com.project.eventlink.event.repository.EventRepository;
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

    @Override
    public List<EventResponse> getList() {

        List<Event> eventList = eventRepository.findAll();
        return eventList.stream().map(event -> eventMapper.toEventResponseModel(event)).toList();
    }

    @Override
    public Long addEvent(Event event) {
        return eventRepository.save(event);
    }
}
