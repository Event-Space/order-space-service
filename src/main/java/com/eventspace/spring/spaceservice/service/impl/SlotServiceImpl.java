package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.repository.SlotRepository;
import com.eventspace.spring.spaceservice.repository.SpaceRepository;
import com.eventspace.spring.spaceservice.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    private final SpaceRepository spaceRepository;

    public Slot createSlot(Long spaceId, LocalDateTime startTime, LocalDateTime endTime) {
        Optional<Space> spaceOptional = spaceRepository.findById(spaceId);
        if (spaceOptional.isPresent()) {
            Slot slot = new Slot();
            slot.setSpace(spaceOptional.get());
            slot.setStartTime(startTime);
            slot.setEndTime(endTime);
            slot.setBooked(false);
            return slotRepository.save(slot);
        } else {
            throw new IllegalArgumentException("Space with ID " + spaceId + " not found");
        }
    }

    public List<Slot> getSlotsBySpaceId(Long spaceId) {
        return slotRepository.findSlotsBySpaceId(spaceId);
    }

    public Slot updateSlotBookingStatus(Long slotId, boolean isBooked) {
        Slot slot = slotRepository.findById(slotId).orElseThrow(() ->
                new IllegalArgumentException("Slot with ID " + slotId + " not found"));
        slot.setBooked(isBooked);
        return slotRepository.save(slot);
    }

    public void deleteSlot(Long slotId) {
        slotRepository.deleteById(slotId);
    }

}

