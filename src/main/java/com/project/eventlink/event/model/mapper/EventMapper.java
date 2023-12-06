package com.project.eventlink.event.model.mapper;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(source = "member.memberId", target = "memberId")
    EventResponse toEventResponseModel(Event event);

}
