package com.project.eventlink.event.repository;

import com.project.eventlink.event.doamin.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EventJpaRepository eventJpaRepository;

    @Override
    public List<Event> findAll() {
        return eventJpaRepository.findAll();
    }

    @Override
    public Long save(Event event) {
        return eventJpaRepository.save(event).getEventId();
    }
}
