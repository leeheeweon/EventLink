package com.project.eventlink.event.controller;

import com.project.eventlink.common.model.CommonResponse;
import com.project.eventlink.event.model.EventResponse;
import com.project.eventlink.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/list")
    public CommonResponse eventList() {
        List<EventResponse> eventList = eventService.getList();
        return CommonResponse.toResponse(HttpStatus.OK, eventList);
    }


}

