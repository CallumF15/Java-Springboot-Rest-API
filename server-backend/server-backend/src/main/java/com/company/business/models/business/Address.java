package com.company.business.models.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.company.business.models.country.Country;


@Entity
@Table(name = "Address", schema = "public") // <-- make sure this matches your PostgreSQL table name
@NoArgsConstructor //generates constructor no args
@AllArgsConstructor //generates constructor with 1 arg for every field in class
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //database column must not contain NULL
    private String street;

    @Column(nullable = false)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50)
    private String county;

    @Column(nullable = false, length = 8)
    private String postcode;

    // One Country -> Many Addresses
    // Many Addresses -> One Country
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
