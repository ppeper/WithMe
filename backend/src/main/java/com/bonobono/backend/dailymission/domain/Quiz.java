package com.bonobono.backend.dailymission.domain;

import javax.persistence.*;

@Entity
@Table(name="quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
