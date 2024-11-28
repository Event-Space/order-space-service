package com.eventspace.spring.spaceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {
    private Long id;
    private Long slotId;
    private Long spaceId;
    private String userEmail;
    private LocalDateTime bookingTime;
    private String status;
}
