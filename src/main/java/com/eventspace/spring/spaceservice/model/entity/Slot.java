package com.eventspace.spring.spaceservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "order_unit")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    @JsonIgnore
    private Space space;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Column(name = "is_booked", nullable = false)
    private boolean isBooked;

    public Slot(Space space, LocalDateTime startTime, LocalDateTime endTime, boolean isBooked) {
        this.space = space;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
    }

}

