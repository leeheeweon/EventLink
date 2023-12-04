package com.project.eventlink.event.service;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.EventResponse;

import java.util.List;

public interface EventService {

    List<EventResponse> getList();

    String addEvent(Event event);

}
