package epicodeCapstone.portfolio.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty(message = "Username è obbligatorio")
        String username,
        @NotEmpty(message = "Email è obbligatoria per la registrazione")
                @Email(message = "Email non valida")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 3, max = 20, message = "La password deve contenere almeno 3 caratteri")
        String password) {
}
