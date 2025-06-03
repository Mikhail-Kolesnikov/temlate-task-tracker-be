package de.upteams.tasktracker.task.validation;

import de.upteams.tasktracker.project.entity.Project;
import de.upteams.tasktracker.task.entity.Task;
import de.upteams.tasktracker.validation.ValidationConstants;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task Entity Validation Tests")
class TaskValidationTest {

    private Validator validator;
    private Project validProject;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        validProject = new Project();
        validProject.setTitle("Valid Project");
    }

    @Test
    void validTask_shouldPassAllValidations() {
        Task task = new Task("Valid Title", "Valid Description", validProject);
        assertNoViolations(task);
    }

    @Test
    void minimalValidTask_shouldPass() {
        Task task = new Task("Val", validProject);
        assertNoViolations(task);
    }

    @Nested
    @DisplayName("Title Field Validation")
    class TitleValidationTests {

        @Test
        @DisplayName("Null title should fail with NotBlank message")
        void nullTitle_shouldFail() {
            Task task = new Task(null, validProject);
            Map<String, List<String>> violations = getViolationsByField(task);

            assertThat(violations).containsKey("title");
            List<String> messages = violations.get("title");
            assertThat(messages).hasSize(1);
            assertThat(messages).containsExactly("Task title cannot be empty");
        }

        @Test
        @DisplayName("Empty title should fail with all validations")
        void emptyTitle_shouldFail() {
            Task task = new Task("", validProject);
            Map<String, List<String>> violations = getViolationsByField(task);

            assertThat(violations).containsKey("title");
            List<String> messages = violations.get("title");
            assertThat(messages).hasSize(3);
            assertThat(messages).containsExactlyInAnyOrder(
                    "Task title cannot be empty",
                    "Task title must be from 3 to 100 characters",
                    "Task title must start with a capital letter and contain at least 3 characters"
            );
        }

        @Test
        @DisplayName("Short title should fail with size and pattern")
        void shortTitle_shouldFail() {
            Task task = new Task("Ab", validProject);
            Map<String, List<String>> violations = getViolationsByField(task);

            assertThat(violations).containsKey("title");
            List<String> messages = violations.get("title");
            assertThat(messages).hasSize(2);
            assertThat(messages).containsExactlyInAnyOrder(
                    "Task title must be from 3 to 100 characters",
                    "Task title must start with a capital letter and contain at least 3 characters"
            );
        }

        @Test
        @DisplayName("Invalid pattern should fail with pattern message")
        void invalidPattern_shouldFail() {
            Task task = new Task("invalid", validProject);
            Map<String, List<String>> violations = getViolationsByField(task);

            assertThat(violations).containsKey("title");
            List<String> messages = violations.get("title");
            assertThat(messages).hasSize(1);
            assertThat(messages).containsExactly("Task title must start with a capital letter and contain at least 3 characters");
        }
    }

    @Nested
    @DisplayName("Description Field Validation")
    class DescriptionValidationTests {

        @Test
        @DisplayName("Valid description should pass validation")
        void validDescription_shouldPass() {
            Task task = new Task("Valid Title", "Valid Description 123!", validProject);
            assertNoViolations(task);
        }

        @Test
        @DisplayName("Long description should fail with size message")
        void longDescription_shouldFail() {
            String longDesc = "A".repeat(ValidationConstants.TASK_DESCRIPTION_MAX_LENGTH + 1);
            Task task = new Task("Valid Title", longDesc, validProject);
            Map<String, List<String>> violations = getViolationsByField(task);

            assertThat(violations).containsKey("description");
            List<String> messages = violations.get("description");
            assertThat(messages).hasSize(1);
            assertThat(messages).containsExactly("Task description must not exceed 1000 characters");
        }

        @Test
        @DisplayName("Invalid description pattern should fail")
        void invalidPattern_shouldFail() {
            Task task = new Task("Valid Title", "invalid", validProject);
            Map<String, List<String>> violations = getViolationsByField(task);

            assertThat(violations).containsKey("description");
            List<String> messages = violations.get("description");
            assertThat(messages).hasSize(1);
            assertThat(messages).containsExactly("Task description must start with a capital letter and contain at least 3 characters");
        }
    }

    @Nested
    @DisplayName("Project Field Validation")
    class ProjectValidationTests {

        @Test
        @DisplayName("Valid project should pass validation")
        void validProject_shouldPass() {
            Task task = new Task("Valid Title", validProject);
            assertNoViolations(task);
        }

        @Test
        @DisplayName("Null project should fail with not null message")
        void nullProject_shouldFail() {
            Task task = new Task("Valid Title", (Project) null);
            Map<String, List<String>> violations = getViolationsByField(task);

            assertThat(violations).containsKey("project");
            List<String> messages = violations.get("project");
            assertThat(messages).hasSize(1);
            assertThat(messages).containsExactly("Task must be linked to a project");
        }
    }

    @Test
    void multipleViolations_shouldReportAllErrorsByField() {
        Task task = new Task("", null);
        Map<String, List<String>> violations = getViolationsByField(task);

        assertThat(violations).containsKeys("title", "project");
        assertThat(violations.get("title")).hasSize(3)
                .containsExactlyInAnyOrder(
                        "Task title cannot be empty",
                        "Task title must be from 3 to 100 characters",
                        "Task title must start with a capital letter and contain at least 3 characters"
                );
        assertThat(violations.get("project")).hasSize(1)
                .containsExactly("Task must be linked to a project");
    }

    private void assertNoViolations(Task task) {
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertThat(violations).isEmpty();
    }

    private Map<String, List<String>> getViolationsByField(Task task) {
        return validator.validate(task).stream()
                .collect(Collectors.groupingBy(
                        v -> v.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())
                ));
    }
}