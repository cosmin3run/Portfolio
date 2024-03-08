package epicodeCapstone.portfolio.repositories;

import epicodeCapstone.portfolio.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EducationDAO extends JpaRepository<Education, UUID> {
}
