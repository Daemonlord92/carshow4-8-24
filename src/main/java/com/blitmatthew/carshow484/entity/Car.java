package com.blitmatthew.carshow484.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private String vin;
    private Integer year; //year.intValue
    private Double mileage;
}
