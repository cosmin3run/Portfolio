package epicodeCapstone.portfolio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    public PostContent(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
