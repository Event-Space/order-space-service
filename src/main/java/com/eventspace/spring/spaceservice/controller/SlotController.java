package com.eventspace.spring.spaceservice.controller;

import com.eventspace.spring.spaceservice.dto.BookingDto;
import com.eventspace.spring.spaceservice.model.entity.Booking;
import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/slots")
@RequiredArgsConstructor
@CrossOrigin("*")
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

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllSlots() {
        List<Booking>  slots = slotService.getAllBookings();
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

    @PostMapping("/book/{slotId}")
    public ResponseEntity<BookingDto> bookSlot(
            @PathVariable Long slotId,
            @RequestParam String userEmail) {
        try {
            Booking booking = slotService.bookSlot(slotId, userEmail);
            BookingDto bookingDTO = mapToDTO(booking);
            return new ResponseEntity<>(bookingDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/bookings/{spaceId}")
    public ResponseEntity<List<BookingDto>> getBookingsBySpaceId(@PathVariable Long spaceId) {
        List<Booking> bookings = slotService.getBookingsBySpaceId(spaceId);
        List<BookingDto> bookingDTOs = bookings.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
    }

    @GetMapping("/bookings/email")
    public List<BookingDto> getBookingsByEmail(@RequestParam String userEmail) {
        return slotService.getBookingsByUserEmail(userEmail).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/bookings/byId/{id}")
    public BookingDto getBookingsById(@PathVariable Long id) {
        return mapToDTO(slotService.getBookingsById(id));
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        try {
            slotService.cancelBooking(bookingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/bookings/{bookingId}/slot")
    public ResponseEntity<BookingDto> updateBookingSlot(
            @PathVariable Long bookingId,
            @RequestParam Long newSlotId) {
        try {
            Booking updatedBooking = slotService.updateBookingSlot(bookingId, newSlotId);
            BookingDto bookingDTO = mapToDTO(updatedBooking);
            return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



    private BookingDto mapToDTO(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getSlot(),
                booking.getSlot().getSpace(),
                booking.getUserEmail(),
                booking.getBookingTime(),
                booking.getStatus()
        );
    }

    @PatchMapping("/bookings/{bookingId}/status")
    public ResponseEntity<BookingDto> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam String status) {
        try {
            Booking updatedBooking = slotService.updateBookingStatus(bookingId, status);
            BookingDto bookingDTO = mapToDTO(updatedBooking);
            return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
