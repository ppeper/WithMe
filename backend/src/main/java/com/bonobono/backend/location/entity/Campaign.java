package com.bonobono.backend.location.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean completionStatus;

    @Column(nullable = false)
    private String authority;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id", nullable = false)
    private Location location;

    @Builder
    public Campaign(String name, LocalDate startDate, LocalDate endDate, String authority, String url, Location location){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.authority = authority;
        this.url = url;
        this.location = location;
    }


}
