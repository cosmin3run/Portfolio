package epicodeCapstone.portfolio.payloads;

import epicodeCapstone.portfolio.entities.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PostContentDTO(
        @NotEmpty(message = "Inserisci un titolo")
        String title,
        @NotEmpty(message = "Inserisci il contenuto del post")
        String content,
        String image,
        @NotNull
        Post post
) {
}
