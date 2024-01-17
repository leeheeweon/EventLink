package com.project.eventlink.event.controller;

import com.project.eventlink.common.model.CommonResponse;
import com.project.eventlink.event.model.CreateEventRequestModel;
import com.project.eventlink.event.model.DeleteEventRequestModel;
import com.project.eventlink.event.model.EventResponse;
import com.project.eventlink.event.model.UpdateRequestModel;
import com.project.eventlink.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        List<EventResponse> eventList = eventService.getEventList();
        return CommonResponse.toResponse(HttpStatus.OK, eventList);
    }

    @PostMapping("/add")
    public CommonResponse add(CreateEventRequestModel createEventRequestModel) {
        Long eventId = eventService.addEvent(createEventRequestModel);
        return CommonResponse.toResponse(HttpStatus.OK, eventId);
    }

    @GetMapping("/{eventId}")
    public CommonResponse findEvent(@PathVariable Long eventId) {
        return CommonResponse.toResponse(HttpStatus.OK, eventService.getEvent(eventId));
    }

    @DeleteMapping("/delete")
    public CommonResponse deleteEvent(DeleteEventRequestModel deleteEventRequestModel) {
        eventService.deleteEvent(deleteEventRequestModel);
        return CommonResponse.toResponse(HttpStatus.OK, deleteEventRequestModel.eventId());
    }

    @PutMapping("/update")
    public CommonResponse updateEvent(UpdateRequestModel updateRequestModel) {
        eventService.updateEvent(updateRequestModel);
        return CommonResponse.toResponse(HttpStatus.OK, updateRequestModel.eventId());
    }
}

