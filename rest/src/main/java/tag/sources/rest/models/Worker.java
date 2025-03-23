package tag.sources.rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static tag.sources.rest.models.constants.WorkerValidationConstants.WORKER_BIRTH_DATE_MESSAGE;
import static tag.sources.rest.models.constants.WorkerValidationConstants.WORKER_NAME_MESSAGE;

@Entity
@Table(name = "workers")
@Getter
@Setter
public class Worker {
    @Id
    @Column(name = "id", columnDefinition="bigserial")
    private Long id;
    @NotBlank(message = WORKER_NAME_MESSAGE)
    @Column(name = "fio", nullable = false)
    private String fio;
    @NotNull(message = WORKER_BIRTH_DATE_MESSAGE)
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
}
