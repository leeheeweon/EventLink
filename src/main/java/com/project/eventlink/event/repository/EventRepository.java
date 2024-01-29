package com.project.eventlink.event.repository;

import com.project.eventlink.event.doamin.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
        Event findByEventId(Long eventId);

        List<Event> findAll();
}
