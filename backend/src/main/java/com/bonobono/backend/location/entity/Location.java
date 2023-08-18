package com.bonobono.backend.location.entity;

import com.bonobono.backend.character.domain.LocationOurCharacter;
import com.bonobono.backend.community.report.entity.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double centerLatitude;

    @Column(nullable = false)
    private double centerLongitude;

    @Column(nullable = false)
    private double lowerLatitude;

    @Column(nullable = false)
    private double leftLongitude;

    @Column(nullable = false)
    private double upperLatitude;

    @Column(nullable = false)
    private double rightLongitude;


    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Campaign> campaigns = new ArrayList<>();

    @Builder
    public Location(String name,
                    double centerLatitude,
                    double centerLongitude,
                    double lowerLatitude,
                    double leftLongitude,
                    double upperLatitude,
                    double rightLongitude) {
        this.name = name;
        this.centerLatitude = centerLatitude;
        this.centerLongitude = centerLongitude;
        this.lowerLatitude = lowerLatitude;
        this.leftLongitude = leftLongitude;
        this.upperLatitude = upperLatitude;
        this.rightLongitude = rightLongitude;
    }

}
