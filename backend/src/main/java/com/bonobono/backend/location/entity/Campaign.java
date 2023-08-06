package com.bonobono.backend.location.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean completionStatus;

    private String authority;

    @ManyToOne
    @JoinColumn(name="location_id", nullable = false)
    private Location location;


}
