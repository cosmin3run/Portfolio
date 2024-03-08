package epicodeCapstone.portfolio.payloads;

import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.enums.EducationLevel;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EducationDTO(
        @NotEmpty(message = "Inserire l'ente emittente")
        String trainingInstitution,
        @NotEmpty(message = "Inserire una descrizione")
        String description,
        @NotEmpty(message = "Inserire il livello di educazione")
        EducationLevel educationLevel,
        LocalDate graduationDate,
        UserInfo userInfo
) {
}
