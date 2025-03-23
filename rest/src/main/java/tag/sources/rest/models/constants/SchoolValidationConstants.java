package tag.sources.rest.models.constants;

public class SchoolValidationConstants {
    public static final String SCHOOL_TITLE_MESSAGE = "Title must not be empty";

    public static final String SCHOOL_MIN_AGE_MESSAGE = "Age should be greater than or equal to 1920";
    public static final Integer SCHOOL_MIN_AGE_VALUE = 1920;
    public static final String SCHOOL_MAX_AGE_MESSAGE = "Age should be smaller than or equal to 2024";
    public static final Integer SCHOOL_MAX_AGE_VALUE = 2024;

    public static final String SCHOOL_DIRECTOR_MESSAGE = "Director must not be empty";

    public static final String SCHOOL_MIN_RATING_MESSAGE = "Rating should be greater than or equal to 1";
    public static final Integer SCHOOL_MIN_RATING_VALUE = 1;
    public static final String SCHOOL_MAX_RATING_MESSAGE = "Rating should be smaller than or equal to 5";
    public static final Integer SCHOOL_MAX_RATING_VALUE = 5;

    private SchoolValidationConstants() {}
}
