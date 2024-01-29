package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.event.model.*;
import com.project.eventlink.event.model.CreateEventRequestModel;
import com.project.eventlink.event.model.DeleteEventRequestModel;
import com.project.eventlink.event.model.FindEventListModel;
import com.project.eventlink.event.model.FindEventModel;
import com.project.eventlink.event.model.UpdateEventRequestModel;
import com.project.eventlink.event.service.EventService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest extends BaseSpringBootTest {

    @Autowired
    private EventService eventService;

    @Test
    @Transactional
    @DisplayName("이벤트 리스트를 가져온다")
    void getAllEventList() {

        //given
        CreateEventRequestModel createEventRequestModel1 = new CreateEventRequestModel("new_event1", 1000, "test");
        CreateEventRequestModel createEventRequestModel2 = new CreateEventRequestModel("new_event2", 2000, "test");
        CreateEventRequestModel createEventRequestModel3 = new CreateEventRequestModel("new_event3", 3000, "test");

        //when
        Long eventId1 = eventService.addEvent(createEventRequestModel1);
        Long eventId2 = eventService.addEvent(createEventRequestModel2);
        Long eventId3 = eventService.addEvent(createEventRequestModel3);

        List<FindEventListModel> eventList = eventService.getEventList();


        //then
        //data.sql저장된것 1개 있음
        assertThat(eventList.size()).isEqualTo(4);
    }

    @Test
    @Transactional
    @DisplayName("이벤트 저장한다.")
    void addEventTest() {
        //given
        CreateEventRequestModel createEventRequestModel = new CreateEventRequestModel("new_event", 1000, "test");

        //when
        Long eventId = eventService.addEvent(createEventRequestModel);

        FindEventModel findEventModel = eventService.getEvent(eventId);
        //then
        assertThat(findEventModel).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("이벤트 삭제한다.")
    void deleteEventTest() {
        //given
        CreateEventRequestModel createEventRequestModel = new CreateEventRequestModel("new_event", 1000, "test");

        //when
        Long eventId = eventService.addEvent(createEventRequestModel);
        eventService.deleteEvent(new DeleteEventRequestModel(eventId));
        List<FindEventListModel> eventList = eventService.getEventList();

        //then
        //data.sql 저장된것 1개있음
        assertThat(eventList.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DisplayName("이벤트 업데이트한다.")
    void updateEventTest() {
        //given
        CreateEventRequestModel createEventRequestModel = new CreateEventRequestModel("new_event", 1000, "test");

        //when
        Long eventId = eventService.addEvent(createEventRequestModel);
        UpdateEventRequestModel updateEventRequestModel = new UpdateEventRequestModel(eventId, "updated", 2000);

        eventService.updateEvent(updateEventRequestModel);

        FindEventModel event = eventService.getEvent(eventId);

        //then
        //data.sql 저장된것 1개있음
        assertThat(event.name()).isEqualTo(updateEventRequestModel.name());
    }

}
