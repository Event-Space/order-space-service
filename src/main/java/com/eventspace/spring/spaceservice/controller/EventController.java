package com.eventspace.spring.spaceservice.controller;


import com.eventspace.spring.spaceservice.dto.EventDto;
import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.model.entity.Event;
import com.eventspace.spring.spaceservice.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Event createEvent(@RequestPart("eventRequest") String spaceRequestJson) throws JsonProcessingException {
        EventDto eventDto = new ObjectMapper().readValue(spaceRequestJson, EventDto.class);

        return eventService.createEvent(eventDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestPart("eventRequest") String spaceRequestJson) throws JsonProcessingException{
        try {
            EventDto eventDto = new ObjectMapper().readValue(spaceRequestJson, EventDto.class);
            Event updatedEvent = eventService.updateEvent(id, eventDto);
            return ResponseEntity.ok(updatedEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{createdBy}")
    public List<Event> getEventsByUser(@PathVariable String createdBy) {
        return eventService.getEventsByUser(createdBy);
    }
}



