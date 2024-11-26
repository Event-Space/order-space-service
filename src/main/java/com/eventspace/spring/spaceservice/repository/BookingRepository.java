package com.eventspace.spring.spaceservice.repository;

import com.eventspace.spring.spaceservice.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingsBySlot_Space_Id(Long spaceId);
    List<Booking> findBookingBySlot_Id(Long slotId);
    List<Booking> findBookingsByUserEmail(String email);
}
