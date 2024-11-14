package com.eventspace.spring.spaceservice.service;

import com.eventspace.spring.spaceservice.model.entity.Event;
import org.kenuki.securitymodule.sessions.SessionMe;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface EventService {

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long id);

    Event createEvent(Event event);

    Event updateEvent(Long id, Event eventDetails);

    void deleteEvent(Long id);

    List<Event> getEventsByUser(String createdBy);
}


