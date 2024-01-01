package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.event.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest extends BaseSpringBootTest {

    @MockBean
    private EventService eventService;

    @ParameterizedTest
    @ValueSource(longs = {0})
    @DisplayName("이벤트 리스트를 가져온다")
    void getAllEventList(Long value) {

        //given
        Event newEvent = Event.builder()
                .eventId(value)
                .name("new_event")
                .minPrice(1000)
                .build();

        //when
        Long eventId = eventService.addEvent(newEvent);

        //then
        assertThat(eventId).isEqualTo(value);
    }

}
