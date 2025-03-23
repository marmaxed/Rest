package tag.sources.rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static tag.sources.rest.models.constants.StudentValidationConstants.*;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    @Column(name = "id", columnDefinition="bigserial")
    private Long id;
    @NotBlank(message = STUDENT_NAME_MESSAGE)
    @Column(name = "fio", nullable = false)
    private String fio;
    @NotNull(message = STUDENT_BIRTH_DATE_MESSAGE)
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @ManyToOne
    @NotNull(message = STUDENT_SCHOOL_CLASS_MESSAGE)
    @JoinColumn(name = "school_class", referencedColumnName = "id")
    private SchoolClass schoolClass;
}
