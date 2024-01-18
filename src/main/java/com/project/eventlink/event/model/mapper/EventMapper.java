package com.project.eventlink.event.model.mapper;

import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.FindEventListModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(source = "member.memberId", target = "memberId")
    FindEventListModel toEventResponseModel(Event event);

}
