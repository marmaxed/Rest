package tag.sources.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tag.sources.rest.models.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
