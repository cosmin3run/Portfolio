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
        @NotEmpty(message = "")
        @Size(min = 3, max = 20, message = "Password must have at least 3 characters.")
        String password) {
}
