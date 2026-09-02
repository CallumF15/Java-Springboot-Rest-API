package com.company.business.models.business;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//| Strategy   | Meaning                    |
//| ---------- | -------------------------- |
//| `IDENTITY` | Database auto-increment    |
//| `AUTO`     | JPA chooses                |
//| `SEQUENCE` | Uses DB sequence           |
//| `TABLE`    | Uses table to generate IDs |

//@Transient - Field not stored in database.

@Entity
@NoArgsConstructor //gens constructor no args
@AllArgsConstructor //gens constructor with 1 arg for every field in class
@Getter
@Setter
public class Sector {

    // Using @GeneratedValue without a strategy defaults to AUTO, allowing Hibernate to choose
    // the ID generation strategy based on the database and JPA provider.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses the database's identity column to automatically generate a unique ID when a new record is inserted.
    private Long id;

    @Column(nullable = false, unique = true) //don't want duplicate sectors
    private String name;

    @OneToMany(mappedBy = "sector") // "sector" refers to the field in Industry
    @JsonManagedReference
    private List<Industry> industries = new ArrayList<>();
}
