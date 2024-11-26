package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.model.entity.Booking;
import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.repository.BookingRepository;
import com.eventspace.spring.spaceservice.repository.SlotRepository;
import com.eventspace.spring.spaceservice.repository.SpaceRepository;
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

}
