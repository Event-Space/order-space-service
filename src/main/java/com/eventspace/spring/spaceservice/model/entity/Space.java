package com.eventspace.spring.spaceservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String location;
    private Integer size;
    private Integer maxCapacity;
    private Double baseRentalCost;
    @Column(name = "image_url")
    private String imageUrl;
}
