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

    private double leftlatitude;
    private double rightlatitude;

    private double leftlongitude;
    private double rightlongitude;


    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Campaign> campaigns = new ArrayList<>();

//    @OneToMany(mappedBy = "location")
//    private List<LocationOurCharacter> locationOurCharacters = new ArrayList<>();

    @Builder
    public Location(String name, double latitude, double longitude, double leftlatitude, double rightlatitude, double leftlongitude, double rightlongitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.leftlatitude = leftlatitude;
        this.rightlatitude = rightlatitude;
        this.leftlongitude = leftlongitude;
        this.rightlongitude = rightlongitude;
    }

//    광안리 해수욕장 35.153227 , 129.118647
    //가장 오른쪽 35.155122, 129.123544 / 왼쪽 35.146578, 129,114926 범위: 0.008544, 0.008618
//    춘장대 해수욕장 36.163966, 126.422946
//    일산 해수욕장 35.495579, 129.430800
//    웅천친수공원 해수욕장 34.747040, 127.667736

}
