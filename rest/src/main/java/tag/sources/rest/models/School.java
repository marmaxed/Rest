package tag.sources.rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static tag.sources.rest.models.constants.SchoolValidationConstants.*;

@Entity
@Table(name = "schools")
@Getter
@Setter
public class School {
    @Id
    @Column(name = "id", columnDefinition="bigserial")
    private Long id;
    @Column(name = "title", nullable = false)
    @NotBlank(message = SCHOOL_TITLE_MESSAGE)
    private String title;
    @Min(value = 1920, message = SCHOOL_MIN_AGE_MESSAGE)
    @Max(value = 2024, message = SCHOOL_MAX_AGE_MESSAGE)
    @Column(name = "year", nullable = false)
    private Integer year;
    @OneToOne
    @NotNull(message = SCHOOL_DIRECTOR_MESSAGE)
    //Fail when worker fair - we must select new before
    @JoinColumn(name = "director", referencedColumnName = "id")
    private Worker director;
    @Min(value = 1, message = SCHOOL_MIN_RATING_MESSAGE)
    @Min(value = 5, message = SCHOOL_MAX_RATING_MESSAGE)
    @Column(name = "rating", nullable = false)
    private Integer rating;
}
