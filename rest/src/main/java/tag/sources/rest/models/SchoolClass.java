package tag.sources.rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static tag.sources.rest.models.constants.SchoolClassValidationConstants.*;

@Entity
@Table(name = "school_classes")
@Getter
@Setter
public class SchoolClass {
    @Id
    @Column(name = "id", columnDefinition="bigserial")
    private Long id;
    @NotBlank(message = SCHOOL_CLASS_NAME_MESSAGE)
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull(message = SCHOOL_CLASS_COUNT_MESSAGE)
    @Min(value = 1, message = SCHOOL_CLASS_COUNT_MESSAGE)
    @Column(name = "count", nullable = false)
    private Integer count;
    @OneToOne
    @NotNull(message = SCHOOL_CLASS_TEACHER_MESSAGE)
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    //Fail when worker fair - we must select new before
    private Worker teacher;
}
