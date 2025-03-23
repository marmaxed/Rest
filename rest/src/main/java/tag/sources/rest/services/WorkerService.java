package tag.sources.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tag.sources.rest.models.Worker;
import tag.sources.rest.repositories.WorkerRepository;

import java.util.List;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    public Worker getById(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker update(Worker worker) {
        return workerRepository.save(worker);
    }

    public void delete(Worker worker) {
        workerRepository.delete(worker);
    }
}
