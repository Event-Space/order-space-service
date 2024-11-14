package com.eventspace.spring.spaceservice.repository;



import com.eventspace.spring.spaceservice.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCreatedBy(String createdBy);

}

