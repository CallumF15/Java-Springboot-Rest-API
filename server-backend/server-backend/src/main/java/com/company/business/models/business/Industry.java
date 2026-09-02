package com.company.business.models.business;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor //gens constructor no args
@AllArgsConstructor //gens constructor with 1 arg for every field in class
@Getter
@Setter
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sector_id") // foreign key column
    private Sector sector;
}
