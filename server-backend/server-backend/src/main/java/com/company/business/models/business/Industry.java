package com.company.business.models.business;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sector_id") // foreign key column
    private Sector sector;
}
