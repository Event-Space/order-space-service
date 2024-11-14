package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.dto.EventDto;
import com.eventspace.spring.spaceservice.mapper.EventMapper;
import com.eventspace.spring.spaceservice.model.entity.Event;
import com.eventspace.spring.spaceservice.repository.EventRepository;
import com.eventspace.spring.spaceservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(EventDto event) {
        Event event1 = eventMapper.eventRequestToEvent(event);
        return eventRepository.save(event1);
    }

    public Event updateEvent(Long id, EventDto eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));

        event.setName(eventDetails.getName());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        event.setCreatedBy(eventDetails.getCreatedBy());

        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
    public List<Event> getEventsByUser(String createdBy) {
        return eventRepository.findByCreatedBy(createdBy);
    }
}


