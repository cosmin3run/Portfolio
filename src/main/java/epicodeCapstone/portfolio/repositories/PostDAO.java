package epicodeCapstone.portfolio.repositories;

import epicodeCapstone.portfolio.entities.Post;
import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostDAO extends JpaRepository<Post, UUID> {
//    Optional<UserInfo> findByUserInfo(UserInfo userInfo);
    List<Post> findByUserInfo(UserInfo userInfo);
    List<Post> findAllByUserInfoId(UUID userInfo);
}
