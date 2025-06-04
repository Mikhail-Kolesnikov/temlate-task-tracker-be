package de.upteams.tasktracker.validation;

import de.upteams.tasktracker.collaborator.entity.Collaborator;
import de.upteams.tasktracker.collaborator.entity.ProjectRoles;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollaboratorValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCollaboratorWithNullId() {
        Collaborator collaborator = new Collaborator();
        collaborator.setAppUserId(1L);
        collaborator.setProjectId(2L);
        collaborator.setProjectRolesSet(Set.of(ProjectRoles.DEVELOPER));

        Set<ConstraintViolation<Collaborator>> violations = validator.validate(collaborator);
        assertFalse(violations.isEmpty());
        violations.forEach(v -> System.out.println(v.getMessage()));
    }
}
