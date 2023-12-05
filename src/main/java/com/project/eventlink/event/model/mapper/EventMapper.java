package com.project.eventlink.event.model.mapper;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.EventResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponse toEventResponseModel(Event event);

}
