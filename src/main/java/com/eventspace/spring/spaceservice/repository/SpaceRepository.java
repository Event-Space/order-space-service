package com.eventspace.spring.spaceservice.repository;

import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.model.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    @Query("SELECT s FROM Slot s WHERE s.space.id = :spaceId AND s.isBooked = false AND DATE(s.startTime) = :date")
    List<Slot> findFreeSlotsByDate(@Param("spaceId") Long spaceId, @Param("date") LocalDate date);

}
