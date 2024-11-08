package com.eventspace.spring.spaceservice.service;

import com.eventspace.spring.spaceservice.model.entity.Slot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SlotService {

    Slot createSlot(Long spaceId, LocalDateTime startTime, LocalDateTime endTime);

    List<Slot> getSlotsBySpaceId(Long spaceId);

    Slot updateSlotBookingStatus(Long slotId, boolean isBooked);

    void deleteSlot(Long slotId);


}

