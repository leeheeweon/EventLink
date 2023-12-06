package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.model.EventResponse;
import com.project.eventlink.event.service.EventService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class EventServiceTest extends BaseSpringBootTest {

    @Autowired
    private EventService eventService;

    @Test
    @DisplayName("이벤트 리스트를 가져온다")
    void getAllEventList() {

        //given
        Event newEvent = Event.builder()
                .eventId(1L)
                .name("new_event")
                .minPrice(1000)
                .build();

        eventService.addEvent(newEvent);

        //when
        List<EventResponse> list = eventService.getList();

        //then
        Assertions.assertThat(list).extracting("name").contains("new_event");

    }

}
