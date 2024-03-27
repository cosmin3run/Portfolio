package epicodeCapstone.portfolio.payloads.response;

import java.util.UUID;

public record PostResponseDTO(UUID id, String title, String content ) {
}
