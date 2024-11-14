package com.eventspace.spring.spaceservice.service;

import com.eventspace.spring.spaceservice.dto.EventDto;
import com.eventspace.spring.spaceservice.model.entity.Event;

import java.util.List;
import java.util.Optional;


public interface EventService {

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long id);

    Event createEvent(EventDto event);

    Event updateEvent(Long id, EventDto eventDetails);

    void deleteEvent(Long id);

    List<Event> getEventsByUser(String createdBy);
}


