package epicodeCapstone.portfolio.repositories;

import epicodeCapstone.portfolio.entities.User;
import epicodeCapstone.portfolio.entities.UserInfo;
import epicodeCapstone.portfolio.entities.UserLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository

public interface UserLinkDAO extends JpaRepository<UserLinks, UUID> {
    List<UserLinks> findAllByUserInfo(UserInfo userInfo);
}
