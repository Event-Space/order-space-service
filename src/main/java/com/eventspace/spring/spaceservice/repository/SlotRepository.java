package com.eventspace.spring.spaceservice.repository;

import com.eventspace.spring.spaceservice.model.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findSlotsBySpaceId(Long id);
}
