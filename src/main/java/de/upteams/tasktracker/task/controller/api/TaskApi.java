package de.upteams.tasktracker.task.controller.api;

import de.upteams.tasktracker.security.service.AuthUserDetails;
import de.upteams.tasktracker.task.dto.TaskDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Task controller")
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/tasks")
public interface TaskApi {

    @PostMapping
    TaskDto save(
            @RequestBody
            TaskDto task,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    @GetMapping("/{id}")
    TaskDto getById(
            @PathVariable
            String id,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    @GetMapping("/project/{projectId}")
    List<TaskDto> getAll(
            @PathVariable
            String projectId,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
            );

    @PutMapping
    void update(
            @RequestBody
            TaskDto task,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    @DeleteMapping("/{id}")
    void deleteById(
            @PathVariable
            String id,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );
}
