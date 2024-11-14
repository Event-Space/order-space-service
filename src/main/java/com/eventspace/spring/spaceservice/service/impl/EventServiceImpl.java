package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.model.entity.Event;
import com.eventspace.spring.spaceservice.repository.EventRepository;
import com.eventspace.spring.spaceservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.kenuki.securitymodule.sessions.SessionMe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;


    @Override
    public ResponseEntity<?> createEvent(SessionMe sessionMe, Event event) {
        Map<String, Object> user = sessionMe.getAttributes();
        event.setCreatedBy(user.get("email").toString());
        eventRepository.save(event);
        return ResponseEntity.ok("Event created successfully");
    }

    @Override
    public ResponseEntity<?> updateEvent(Long eventId, SessionMe sessionMe, Event event) {
        Map<String, Object> user = sessionMe.getAttributes();
        Optional<Event> existingEventOpt = eventRepository.findById(eventId);

        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();

            if (!existingEvent.getCreatedBy().equals(user.get("email").toString())) {
                return ResponseEntity.status(403).body("You do not have permission to update this event");
            }

            existingEvent.setName(event.getName());
            existingEvent.setDate(event.getDate());
            existingEvent.setLocation(event.getLocation());
            eventRepository.save(existingEvent);

            return ResponseEntity.ok("Event updated successfully");
        } else {
            return ResponseEntity.status(404).body("Event not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteEvent(Long eventId, SessionMe sessionMe) {
        Map<String, Object> user = sessionMe.getAttributes();
        Optional<Event> eventOpt = eventRepository.findById(eventId);

        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();

            if (!event.getCreatedBy().equals(user.get("email").toString())) {
                return ResponseEntity.status(403).body("You do not have permission to delete this event");
            }

            eventRepository.delete(event);
            return ResponseEntity.ok("Event deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Event not found");
        }
    }

    @Override
    public ResponseEntity<List<Event>> getAllEvents(SessionMe sessionMe) {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @Override
    public ResponseEntity<List<Event>> getMyEvents(SessionMe sessionMe) {
        Map<String, Object> user = sessionMe.getAttributes();
        List<Event> events = eventRepository.findByCreatedBy(user.get("email").toString());
        return ResponseEntity.ok(events);
    }
}


