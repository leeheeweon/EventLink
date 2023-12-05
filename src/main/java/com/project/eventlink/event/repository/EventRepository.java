package com.project.eventlink.event.repository;

import com.project.eventlink.event.doamin.Event;

import java.util.List;

public interface EventRepository {

    List<Event> findAll();

    String save(Event event);
}
