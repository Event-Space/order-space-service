package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.dto.NotificationDTO;
import com.eventspace.spring.spaceservice.model.entity.Booking;
import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.repository.BookingRepository;
import com.eventspace.spring.spaceservice.repository.SlotRepository;
import com.eventspace.spring.spaceservice.repository.SpaceRepository;
import com.eventspace.spring.spaceservice.service.NotificationService;
import com.eventspace.spring.spaceservice.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    private final SpaceRepository spaceRepository;
    private final BookingRepository bookingRepository;
    private final NotificationService notificationService;

    @Override
    public Slot createSlot(Long spaceId, LocalDateTime startTime, LocalDateTime endTime) {
        Space space = spaceRepository.findById(spaceId).orElseThrow(() ->
                new IllegalArgumentException("Space with ID " + spaceId + " not found"));

        Slot slot = new Slot();
        slot.setSpace(space);
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        slot.setBooked(false);
        return slotRepository.save(slot);
    }

    @Override
    public List<Slot> getSlotsBySpaceId(Long spaceId) {
        return slotRepository.findSlotsBySpaceId(spaceId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Slot updateSlotBookingStatus(Long slotId, boolean isBooked) {
        Slot slot = slotRepository.findById(slotId).orElseThrow(() ->
                new IllegalArgumentException("Slot with ID " + slotId + " not found"));
        slot.setBooked(isBooked);
        return slotRepository.save(slot);
    }

    @Override
    public void deleteSlot(Long slotId) {
        slotRepository.deleteById(slotId);
    }

    @Transactional
    @Override
    public Booking bookSlot(Long slotId, String userEmail) {
        Slot slot = slotRepository.findById(slotId).orElseThrow(() ->
                new IllegalArgumentException("Slot with ID " + slotId + " not found"));

        if (slot.isBooked()) {
            throw new IllegalArgumentException("Slot with ID " + slotId + " is already booked.");
        }

        // Mark slot as booked
        slot.setBooked(true);
        slotRepository.save(slot);

        // Create and save a new booking
        Booking booking = new Booking();
        booking.setSlot(slot);
        booking.setUserEmail(userEmail);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        notificationService.sendNotification(
                new NotificationDTO(
                        "You ordered a new space: " + slot.getSpace().getName() +
                                ".\nAt time: " + booking.getBookingTime() + ".",
                        userEmail)
        );

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsBySpaceId(Long spaceId) {
        return bookingRepository.findBookingsBySlot_Space_Id(spaceId);
    }

    @Override
    public List<Booking> getBookingsByUserEmail(String userEmail) {
        return bookingRepository.findBookingsByUserEmail(userEmail);
    }

    @Override
    public Booking getBookingsById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        booking.setStatus(status);
        notificationService.sendNotification(
                new NotificationDTO(
                        "Booking: " + booking.getSlot().getSpace().getName() + " have new status: " + status + ".",
                        booking.getUserEmail())
        );
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new IllegalArgumentException("Booking not found");
        }
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        bookingRepository.deleteById(bookingId);
        notificationService.sendNotification(
                new NotificationDTO(
                        "Booking: " + booking.getSlot().getSpace().getName() + " have been canceled.",
                        booking.getUserEmail()
                )
        );
    }



    public Booking updateBookingSlot(Long bookingId, Long newSlotId) {
        Booking booking = getBookingsById(bookingId);
        Slot newSlot = slotRepository.findById(newSlotId)
                .orElseThrow(() -> new IllegalArgumentException("Slot not found"));

        if (newSlot.isBooked()) {
            throw new IllegalArgumentException("Slot is already booked");
        }

        Slot currentSlot = booking.getSlot();
        currentSlot.setBooked(false);
        newSlot.setBooked(true);

        booking.setSlot(newSlot);
        slotRepository.save(currentSlot);
        slotRepository.save(newSlot);

        return bookingRepository.save(booking);
    }



}
