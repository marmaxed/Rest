package tag.sources.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tag.sources.rest.models.Worker;
import tag.sources.rest.models.Worker;
import tag.sources.rest.services.WorkerService;

import java.util.List;

@RestController
public class WorkerController {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getAllWorkers() {
        return ResponseEntity.ok(workerService.getAll());
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Long id) {
        Worker worker = workerService.getById(id);
        if (worker == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worker);
    }

    @PostMapping("/workers")
    public ResponseEntity<String> saveWorker(@RequestBody Worker worker) {
        try {
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(workerService.save(worker)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/workers/{id}")
    public ResponseEntity<String> updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        Worker toUpdate = workerService.getById(id);
        if (toUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            Worker updated = workerService.update(worker);
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(updated));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/workers/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable Long id) {
        Worker toDelete = workerService.getById(id);
        if (toDelete == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            workerService.delete(toDelete);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
