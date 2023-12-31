package com.project.eventlink.event.service;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.EventResponse;

import java.util.List;

public interface EventService {

    List<EventResponse> getList();

    Long addEvent(Event event);

}
