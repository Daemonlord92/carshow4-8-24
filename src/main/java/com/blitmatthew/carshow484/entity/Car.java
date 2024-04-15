package com.blitmatthew.carshow484.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.Engine;

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
    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    private String vin;
    @NotNull
    private Integer year; //year.intValue
    @NotNull
    private Double mileage;

    @ManyToOne
    @JsonIgnore
    private Owner owner;
    //What is the difference between == and .equals() in Java?


}
