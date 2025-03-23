package tag.sources.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tag.sources.rest.models.School;
import tag.sources.rest.services.SchoolService;

import java.util.List;

@RestController
public class SchoolController {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    public ResponseEntity<List<School>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAll());
    }

    @GetMapping("/schools/{id}")
    public ResponseEntity<School> getById(@PathVariable Long id) {
        School school = schoolService.getById(id);
        if (school == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(school);
    }

    @PostMapping("/schools")
    public ResponseEntity<String> saveSchool(@RequestBody School school) {
        try {
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(schoolService.save(school)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/schools/{id}")
    public ResponseEntity<String> updateSchool(@PathVariable Long id, @RequestBody School school) {
        School toUpdate = schoolService.getById(id);
        if (toUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            School updated = schoolService.update(school);
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(updated));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        School toDelete = schoolService.getById(id);
        if (toDelete == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            schoolService.delete(toDelete);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
