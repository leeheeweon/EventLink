package com.project.eventlink.event.service;

import com.project.eventlink.event.model.CreateEventRequestModel;
import com.project.eventlink.event.model.DeleteEventRequestModel;
import com.project.eventlink.event.model.EventResponse;
import com.project.eventlink.event.model.FindEventModel;
import com.project.eventlink.event.model.UpdateRequestModel;

import java.util.List;

public interface EventService {

    List<EventResponse> getEventList();

    Long addEvent(CreateEventRequestModel event);

    FindEventModel getEvent(Long eventId);

    void deleteEvent(DeleteEventRequestModel deleteEventRequestModel);

    void updateEvent(UpdateRequestModel eventId);
}
