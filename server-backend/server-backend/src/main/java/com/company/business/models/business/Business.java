package com.company.business.models.business;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Table(name = "businesses", schema = "public") // <-- make sure this matches your PostgreSQL table name
@NoArgsConstructor //gens constructor no args
@AllArgsConstructor //gens constructor with 1 arg for every field in class
@Getter
@Setter
public class Business {

    @Id //mark field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tells the database to auto-generate values for this primary key.
    private Long id;

    // ========================
    // Basic Info
    // ========================

    @NotBlank(message = "Title is required") //field can not be blank
    @Size(max = 200) // Restrict length of field to 200 characters
    private String title;

    @Size(max = 2000) // Restrict length of field to 2000 characters
    private String description;

    @ManyToOne
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    // ========================
    // Contact Info
    // ========================

    @Email
    @Size(max = 200)
    private String email;

    @Size(max = 20)
    @Column(nullable = true) //companies might not always have a landline
    private String landlineNumber;

    @Size(max = 20)
    @Column(nullable = true)
    private String phoneNumber;

    @Embedded //there isn't a separate table pulls fields from class tidying code here a little
    private Address address;

    @URL(message = "Website must be a valid URL") //validates the url
    @Size(max = 500)
    @Column(nullable = true) //companies might not always have a website
    private String website;

    // ========================
    // Metadata / Flags
    // ========================

    @Size(max = 500)
    @Column(nullable = true)
    private String logoUrl;

    private Boolean isActive = true; //is the business active?

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
