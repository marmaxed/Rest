package tag.sources.rest.models.constants;

public class SchoolClassValidationConstants {
    public static final String SCHOOL_CLASS_NAME_MESSAGE = "Title must not be empty";

    public static final String SCHOOL_CLASS_COUNT_MESSAGE = "Count should be greater than or equal to 1";
    public static final Integer SCHOOL_CLASS_COUNT_VALUE = 1;

    public static final String SCHOOL_CLASS_TEACHER_MESSAGE = "Teacher must not be null";

    private SchoolClassValidationConstants() {}
}
