package tag.sources.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tag.sources.rest.models.School;
import tag.sources.rest.repositories.SchoolRepository;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAll() {
        return schoolRepository.findAll();
    }

    public School getById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public School save(School school) {
        return schoolRepository.save(school);
    }

    public School update(School school) {
        return schoolRepository.save(school);
    }

    public void delete(School school) {
        schoolRepository.delete(school);
    }
}
