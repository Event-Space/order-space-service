package com.eventspace.spring.spaceservice.controller;


import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/slots")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SlotController {

    private final SlotService slotService;


    @PostMapping("/{spaceId}")
    public ResponseEntity<Slot> createSlot(@PathVariable Long spaceId,
                                           @RequestParam LocalDateTime startTime,
                                           @RequestParam LocalDateTime endTime) {
        Slot slot = slotService.createSlot(spaceId, startTime, endTime);
        return new ResponseEntity<>(slot, HttpStatus.CREATED);
    }

    @GetMapping("/{spaceId}")
    public ResponseEntity<List<Slot>> getSlotsBySpaceId(@PathVariable Long spaceId) {
        List<Slot> slots = slotService.getSlotsBySpaceId(spaceId);
        return new ResponseEntity<>(slots, HttpStatus.OK);
    }

    @PatchMapping("/{slotId}/booking")
    public ResponseEntity<Slot> updateSlotBookingStatus(@PathVariable Long slotId,
                                                        @RequestParam boolean isBooked) {
        Slot updatedSlot = slotService.updateSlotBookingStatus(slotId, isBooked);
        return new ResponseEntity<>(updatedSlot, HttpStatus.OK);
    }

    @DeleteMapping("/{slotId}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long slotId) {
        slotService.deleteSlot(slotId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

