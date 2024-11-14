package com.eventspace.spring.spaceservice.controller;


import com.eventspace.spring.spaceservice.model.entity.Event;
import com.eventspace.spring.spaceservice.service.EventService;
import org.kenuki.securitymodule.annotations.SecureMe;
import org.kenuki.securitymodule.sessions.SessionMe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @SecureMe
    public ResponseEntity<?> createEvent(@RequestBody Event event,
                                         SessionMe sessionMe) {
        return eventService.createEvent(sessionMe, event);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId,
                                         @RequestBody Event event,
                                          SessionMe sessionMe) {
        return eventService.updateEvent(eventId, sessionMe, event);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId,
                                          SessionMe sessionMe) {
        return eventService.deleteEvent(eventId, sessionMe);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(SessionMe sessionMe) {
        return eventService.getAllEvents(sessionMe);
    }

    @GetMapping("/my-events")
    public ResponseEntity<List<Event>> getMyEvents(SessionMe sessionMe) {
        return eventService.getMyEvents(sessionMe);
    }
}


