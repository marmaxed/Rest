package tag.sources.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tag.sources.rest.models.SchoolClass;
import tag.sources.rest.repositories.SchoolClassRepository;

import java.util.List;

@Service
public class SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public List<SchoolClass> getAll() {
        return schoolClassRepository.findAll();
    }

    public SchoolClass getById(Long id) {
        return schoolClassRepository.findById(id).orElse(null);
    }

    public SchoolClass save(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    public SchoolClass update(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    public void delete(SchoolClass schoolClass) {
        schoolClassRepository.delete(schoolClass);
    }
}
