package com.eventspace.spring.spaceservice.controller;

import com.eventspace.spring.spaceservice.model.entity.Booking;
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
public class SlotController {

    private final SlotService slotService;

    // Create a new slot
    @PostMapping("/{spaceId}")
    public ResponseEntity<Slot> createSlot(
            @PathVariable Long spaceId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        try {
            Slot createdSlot = slotService.createSlot(spaceId, startTime, endTime);
            return new ResponseEntity<>(createdSlot, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get all slots for a specific space
    @GetMapping("/{spaceId}")
    public ResponseEntity<List<Slot>> getSlotsBySpaceId(@PathVariable Long spaceId) {
        List<Slot> slots = slotService.getSlotsBySpaceId(spaceId);
        return new ResponseEntity<>(slots, HttpStatus.OK);
    }

    // Update booking status of a slot
    @PatchMapping("/{slotId}")
    public ResponseEntity<Slot> updateSlotBookingStatus(
            @PathVariable Long slotId,
            @RequestParam boolean isBooked) {
        try {
            Slot updatedSlot = slotService.updateSlotBookingStatus(slotId, isBooked);
            return new ResponseEntity<>(updatedSlot, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a slot
    @DeleteMapping("/{slotId}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long slotId) {
        try {
            slotService.deleteSlot(slotId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Book a slot
    @PostMapping("/book/{slotId}")
    public ResponseEntity<Booking> bookSlot(
            @PathVariable Long slotId,
            @RequestParam String userEmail) {
        try {
            Booking booking = slotService.bookSlot(slotId, userEmail);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    // Get all bookings for a specific space
    @GetMapping("/bookings/{spaceId}")
    public ResponseEntity<List<Booking>> getBookingsBySpaceId(@PathVariable Long spaceId) {
        List<Booking> bookings = slotService.getBookingsBySpaceId(spaceId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/bookings/email")
    public List<Booking> getBookingsByEmail(@RequestParam String userEmail) {
        return slotService.getBookingsByUserEmail(userEmail);
    }
}
