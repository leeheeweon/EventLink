package com.project.eventlink.event.service;

import com.project.eventlink.event.model.CreateEventRequestModel;
import com.project.eventlink.event.model.DeleteEventRequestModel;
import com.project.eventlink.event.model.FindEventListModel;
import com.project.eventlink.event.model.FindEventModel;
import com.project.eventlink.event.model.UpdateEventRequestModel;

import java.util.List;

public interface EventService {

    List<FindEventListModel> getEventList();

    Long addEvent(CreateEventRequestModel event);

    FindEventModel getEvent(Long eventId);

    void deleteEvent(DeleteEventRequestModel deleteEventRequestModel);

    void updateEvent(UpdateEventRequestModel eventId);
}
