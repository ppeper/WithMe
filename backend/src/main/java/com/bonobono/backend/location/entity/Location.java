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

    private String name;

    private double latitude;

    private double longitude;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Campaign> campaigns = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<LocationOurCharacter> locationOurCharacters = new ArrayList<>();

    @Builder
    public Location(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

//    광안리 해수욕장 35.153227 , 129.118647
//    춘장대 해수욕장 36.163966, 126.422946
//    일산 해수욕장 35.495579, 129.430800
//    웅천친수공원 해수욕장 34.747040, 127.667736

}
