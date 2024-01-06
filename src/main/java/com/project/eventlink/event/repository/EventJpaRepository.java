package com.project.eventlink.event.repository;

import com.project.eventlink.event.doamin.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {
        Event findByEventId(Long eventId);
}
