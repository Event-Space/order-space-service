package com.eventspace.spring.spaceservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceRequest {
    private String name;
    private String address;
    private String location;
    private Integer size;
    private Integer maxCapacity;
    private Double baseRentalCost;
}
