package tag.sources.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tag.sources.rest.models.SchoolClass;
import tag.sources.rest.services.SchoolClassService;

import java.util.List;

@RestController
public class SchoolClassController {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final SchoolClassService schoolClassService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("/classes")
    public ResponseEntity<List<SchoolClass>> getAllSchoolClasss() {
        return ResponseEntity.ok(schoolClassService.getAll());
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<SchoolClass> getById(@PathVariable Long id) {
        SchoolClass schoolClass = schoolClassService.getById(id);
        if (schoolClass == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schoolClass);
    }

    @PostMapping("/classes")
    public ResponseEntity<String> saveSchoolClass(@RequestBody SchoolClass schoolClass) {
        try {
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(schoolClassService.save(schoolClass)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/classes/{id}")
    public ResponseEntity<String> updateSchoolClass(@PathVariable Long id, @RequestBody SchoolClass schoolClass) {
        SchoolClass toUpdated = schoolClassService.getById(id);
        if (toUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            SchoolClass updated = schoolClassService.update(schoolClass);
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(updated));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<String> deleteSchoolClass(@PathVariable Long id) {
        SchoolClass toDelete = schoolClassService.getById(id);
        if (toDelete == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            schoolClassService.delete(toDelete);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
