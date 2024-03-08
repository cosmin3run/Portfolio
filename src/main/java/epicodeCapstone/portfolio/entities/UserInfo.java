package epicodeCapstone.portfolio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "userInfo")
    private List<UserLinks> links = new ArrayList<>();

    private String descriptionTitle;
    private String descriptionBody;

    @OneToMany(mappedBy = "userInfo")
    private List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<Education> educations = new ArrayList<>();


}
