package epicodeCapstone.portfolio.entities;

import epicodeCapstone.portfolio.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@ToString
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

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    public Education(String trainingInstitution, String description, EducationLevel educationLevel, LocalDate graduationDate) {
        this.trainingInstitution = trainingInstitution;
        this.description = description;
        this.educationLevel = educationLevel;
        this.graduationDate = graduationDate;
    }
}
