package epicodeCapstone.portfolio.payloads;

import epicodeCapstone.portfolio.entities.UserInfo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PostDTO(
        @NotEmpty(message = "Inserire titolo")
        String title,

        LocalDate publicationDate
) {
}
