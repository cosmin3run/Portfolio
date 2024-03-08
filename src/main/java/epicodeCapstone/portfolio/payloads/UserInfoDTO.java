package epicodeCapstone.portfolio.payloads;


import epicodeCapstone.portfolio.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserInfoDTO(
        @NotEmpty(message = "Il nome è obbligatorio.")
        String name,

        @NotEmpty(message = "Il cognome è obbligatorio")
        String surname,
        String avatar,
        User user,
        String descriptionTitle,
        String descriptionBody

        ) {
}
