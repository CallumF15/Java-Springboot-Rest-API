package uk.gov.hmcts.reform.dev.models.business;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "sector") // "sector" refers to the field in Industry
    private List<Industry> industries = new ArrayList<>();

//    public void addIndustry(Industry industry) {
//        industries.add(industry);
//        industry.setSector(this);
//    }
//
//    public void removeIndustry(Industry industry) {
//        industries.remove(industry);
//        industry.setSector(null);
//    }
}
