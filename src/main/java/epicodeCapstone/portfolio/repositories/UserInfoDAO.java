package epicodeCapstone.portfolio.repositories;

import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoDAO extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByUser(User user);

}
