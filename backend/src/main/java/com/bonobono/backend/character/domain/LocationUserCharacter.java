//package com.bonobono.backend.character.domain;
//
//import com.bonobono.backend.location.entity.Location;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class LocationUserCharacter {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "location_id")
//    private Location location;
//
//    @ManyToOne
//    @JoinColumn(name = "user_character_id")
//    private UserCharacter userCharacter;
//
//    @Builder
//    public LocationUserCharacter(Location location, UserCharacter userCharacter) {
//        this.location = location;
//        this.userCharacter=userCharacter;
//    }
//
//}
