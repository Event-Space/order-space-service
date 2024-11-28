package com.eventspace.spring.spaceservice.dto;

import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.model.entity.Space;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {
    private Long id;
    private Slot slot;
    private Space space;
    private String userEmail;
    private LocalDateTime bookingTime;
    private String status;
}
