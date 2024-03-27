package epicodeCapstone.portfolio.payloads;

import epicodeCapstone.portfolio.entities.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.cfg.defs.UUIDDef;

import java.util.UUID;

public record PostContentDTO(

        String title,
        @NotEmpty(message = "Inserisci il contenuto del post")
        String content,
        @NotNull
        UUID postId
) {
}
