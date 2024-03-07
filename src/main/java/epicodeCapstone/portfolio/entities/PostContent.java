package epicodeCapstone.portfolio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "post_content")
public class PostContent {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String content;
    private String image;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    public PostContent(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
