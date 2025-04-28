package de.upteams.tasktracker.project.dto;

import de.upteams.tasktracker.task.dto.TaskDto;
import de.upteams.tasktracker.user.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * Project DTO
 */
@Schema(description = "Data Transfer Object for Project entity")
@Value
public class ProjectDto {

    /**
     * Project ID
     */
    @Schema(description = "Unique identifier of the Project", example = "7", accessMode = Schema.AccessMode.READ_ONLY)
    String id;

    /**
     * Project title
     */
    @Schema(description = "Title of the Project", example = "New Website Development")
    String title;

    /**
     * Project description
     */
    @Schema(
            description = "Detailed description of the Project",
            example = "A Project to develop a new company website"
    )
    String description;

    /**
     * Author of the Project
     */
    @Schema(description = "The User who created the Project", accessMode = Schema.AccessMode.READ_ONLY)
    EmployeeDto owner;

    /**
     * Tasks list for that Project
     */
    @Schema(description = "List of Tasks associated with this Project", accessMode = Schema.AccessMode.READ_ONLY)
    Set<TaskDto> tasks = new HashSet<>();
}
