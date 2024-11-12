package com.eventspace.spring.spaceservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Data
@Table(schema = "order_unit")
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
    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    private List<Slot> slots;
}
