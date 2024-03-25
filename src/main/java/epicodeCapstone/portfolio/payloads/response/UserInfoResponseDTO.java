package epicodeCapstone.portfolio.payloads.response;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UserInfoResponseDTO(
        UUID id,
        String name,

        String surname,
        String linkedin,
        String github,
        String instagram,
        String descriptionTitle,
        String descriptionBody) {
}
