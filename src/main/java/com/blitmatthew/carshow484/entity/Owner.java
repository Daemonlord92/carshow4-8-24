package com.blitmatthew.carshow484.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 3, max = 75)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 75)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "\\b\\d{5,15}\\b", message = "Phone number must be between 5 and 15 digits")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Car> cars;
}
