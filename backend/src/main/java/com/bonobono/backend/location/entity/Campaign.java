package com.bonobono.backend.location.entity;

import lombok.Builder;
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

    @Column(nullable = false)
    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean completionStatus;

    @Column(nullable = false)
    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id", nullable = false)
    private Location location;

    @Builder
    public Campaign(String name, LocalDateTime startDate, LocalDateTime endDate, String authority, Location location){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.authority = authority;
        this.location = location;
    }


}
