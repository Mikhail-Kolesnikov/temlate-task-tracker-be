package de.upteams.tasktracker.validation;

/**
 * Constants for validation rules across the application
 */
public final class ValidationConstants {

    private ValidationConstants() {
    }

    // ========== TASK VALIDATION ==========

    /**
     * Regex for task title: starts with capital letter,
     * can contain letters, numbers, spaces, minimum 3 characters
     */
    public static final String TASK_TITLE_REGEX = "[A-Z][a-zA-Z1-9 ]{2,}";

    /**
     * Regex for task description: starts with capital letter,
     * can contain letters, numbers, punctuation, minimum 3 characters
     */
    public static final String TASK_DESCRIPTION_REGEX = "[A-Z][a-zA-Z1-9,.%:?&!$;*() ]{2,}";

    /**
     * Minimum length for task title
     */
    public static final int TASK_TITLE_MIN_LENGTH = 3;

    /**
     * Maximum length for task title
     */
    public static final int TASK_TITLE_MAX_LENGTH = 100;

    /**
     * Maximum length for task description
     */
    public static final int TASK_DESCRIPTION_MAX_LENGTH = 1000;
}