package tag.sources.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tag.sources.rest.models.Student;
import tag.sources.rest.services.StudentService;

import java.util.List;

@RestController
public class StudentController {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        try {
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(studentService.save(student)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student toUpdate = studentService.getById(id);
        if (toUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            Student updated = studentService.update(student);
            return ResponseEntity.ok().body(MAPPER.writer().writeValueAsString(updated));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Student toDelete = studentService.getById(id);
        if (toDelete == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            studentService.delete(toDelete);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
