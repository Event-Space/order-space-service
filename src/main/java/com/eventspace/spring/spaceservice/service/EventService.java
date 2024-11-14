package com.eventspace.spring.spaceservice.service;

import com.eventspace.spring.spaceservice.model.entity.Event;
import org.kenuki.securitymodule.sessions.SessionMe;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EventService {

    ResponseEntity<?> createEvent(SessionMe sessionMe, Event event);

    ResponseEntity<?> updateEvent(Long eventId, SessionMe sessionMe, Event event);

    ResponseEntity<?> deleteEvent(Long eventId, SessionMe sessionMe);

    ResponseEntity<List<Event>> getAllEvents(SessionMe sessionMe);

    ResponseEntity<List<Event>> getMyEvents(SessionMe sessionMe);
}


