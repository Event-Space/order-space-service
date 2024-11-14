package com.eventspace.spring.spaceservice.mapper;

import com.eventspace.spring.spaceservice.dto.EventDto;
import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.model.entity.Event;
import com.eventspace.spring.spaceservice.model.entity.Space;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public Event eventRequestToEvent(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }

        Event event = new Event();
        event.setName(eventDto.getName());
        event.setCreatedBy(eventDto.getCreatedBy());
        event.setLocation(eventDto.getLocation());
        event.setDate(eventDto.getDate());

        return event;
    }
}
