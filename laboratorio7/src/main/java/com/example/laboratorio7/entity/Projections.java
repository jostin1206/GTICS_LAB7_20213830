package com.example.laboratorio7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movieprojections")
public class Projections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movies movies;


    @Column(name = "projectionDate")
    private String projection;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Rooms rooms;

    @Column(name = "availableSeats")
    private int available;

}
