package epicodeCapstone.portfolio.payloads.response;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UserInfoResponseDTO(
        UUID id,
                                  String name,

                                  String surname,
                                  String descriptionTitle,
                                  String descriptionBody) {
}
