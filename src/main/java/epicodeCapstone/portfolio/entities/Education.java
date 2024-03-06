package epicodeCapstone.portfolio.entities;

import epicodeCapstone.portfolio.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue
    private UUID id;
    private String trainingInstitution;
    private String description;
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
    private LocalDate graduationDate;

}
