
package de.upteams.tasktracker.task.controller.api;

import de.upteams.tasktracker.security.service.AuthUserDetails;
import de.upteams.tasktracker.task.dto.TaskDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  Добавлено: описание контроллера
@Tag(name = "Task controller", description = "Controller for task-related operations")
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/tasks")
public interface TaskApi {

    //  Метод для создания задачи
    @Operation(summary = "Create Task", description = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task successfully created",
                    content = @Content(schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid task input")
    })
    @PostMapping
    TaskDto save(
            @RequestBody(
                    required = true,
                    description = "Task data to create"
            )
            TaskDto task,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    //  Метод для получения задачи по ID
    @Operation(summary = "Get Task by ID", description = "Retrieve a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = @Content(schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    TaskDto getById(
            @PathVariable
            @Parameter(required = true, description = "Task ID to retrieve")
            String id,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    //  Метод для получения всех задач по projectId
    @Operation(summary = "Get Tasks for Project", description = "Retrieve all tasks by project ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of tasks returned")
    })
    @GetMapping("/project/{projectId}")
    List<TaskDto> getAll(
            @PathVariable
            @Parameter(required = true, description = "Project ID to list tasks")
            String projectId,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    //  Метод для обновления задачи
    @Operation(summary = "Update Task", description = "Update an existing task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping
    void update(
            @RequestBody(
                    required = true,
                    description = "Task data to update"
            )
            TaskDto task,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    //  Метод для удаления задачи по ID
    @Operation(summary = "Delete Task", description = "Delete a task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    void deleteById(
            @PathVariable
            @Parameter(required = true, description = "Task ID to delete")
            String id,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );
}
