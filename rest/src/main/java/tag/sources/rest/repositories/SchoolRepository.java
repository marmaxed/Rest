package tag.sources.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tag.sources.rest.models.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
