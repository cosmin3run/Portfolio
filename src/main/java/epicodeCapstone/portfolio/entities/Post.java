package epicodeCapstone.portfolio.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

public class Post {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private LocalDate publicationDate;
    private PostContent postContent;
    private String mainImg;
    
}
