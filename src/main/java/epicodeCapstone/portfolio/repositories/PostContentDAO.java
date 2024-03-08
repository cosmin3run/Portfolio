package epicodeCapstone.portfolio.repositories;

import epicodeCapstone.portfolio.entities.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostContentDAO extends JpaRepository<PostContent, UUID> {
}
