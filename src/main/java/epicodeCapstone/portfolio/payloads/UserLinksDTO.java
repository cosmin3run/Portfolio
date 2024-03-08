package epicodeCapstone.portfolio.payloads;

import epicodeCapstone.portfolio.entities.UserInfo;
import jakarta.validation.constraints.NotEmpty;

public record UserLinksDTO(
      @NotEmpty(message = "Il titolo è obbligatorio")
      String title,
      @NotEmpty(message = "Il link è obbligatorio")
      String url,
      String imageUrl,
      @NotEmpty(message = "Inserisci l'id di userInfo")
      UserInfo userInfo
) {
}
