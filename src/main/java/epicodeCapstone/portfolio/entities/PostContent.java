package epicodeCapstone.portfolio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post_content")
public class PostContent {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String content;
    private String image;

}
