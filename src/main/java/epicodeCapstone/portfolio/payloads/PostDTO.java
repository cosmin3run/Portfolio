package epicodeCapstone.portfolio.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record PostDTO(
        @NotEmpty(message = "Inserire titolo")
        String title,
        @NotEmpty(message = "Inserire data di pubblicazione")
        LocalDate publicationDate,
        String mainImg
) {
}
