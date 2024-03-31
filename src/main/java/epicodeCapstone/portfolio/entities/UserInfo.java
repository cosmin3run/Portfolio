package epicodeCapstone.portfolio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import epicodeCapstone.portfolio.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")

public class UserInfo {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String avatar;
    private String linkedin;
    private String github;
    private String instagram;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties("userInfo")
    private User user;

//    @OneToMany(mappedBy = "userInfo")
//    private List<UserLinks> links = new ArrayList<>();

    private String descriptionTitle;
    @Column(columnDefinition = "TEXT")
    private String descriptionBody;

    @OneToMany(mappedBy = "userInfo")
    private List<Post> post = new ArrayList<>();

//    @OneToMany(mappedBy = "userInfo")
//    private List<Education> educations = new ArrayList<>();

    public UserInfo(User user) {
        this.user = user;
    }
}
