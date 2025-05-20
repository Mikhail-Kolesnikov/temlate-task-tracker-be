package de.upteams.tasktracker.project.controller.api;

import de.upteams.tasktracker.project.dto.ProjectDto;
import de.upteams.tasktracker.project.dto.request.ProjectCreateDto;
<<<<<<< HEAD
import de.upteams.tasktracker.security.service.AuthUserDetails;
=======
>>>>>>> 260ae4d (Initial commit)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project API description for Swagger
 */
@Tag(name = "Project controller", description = "Controller for various operations with Projects")
@RequestMapping("/api/v1/projects")
@PreAuthorize("isAuthenticated()")
public interface ProjectApi {

    @Operation(summary = "Save Project", description = "Save new Project to the Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project successfully created",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Project fields values")
    })
    @PostMapping
    ProjectDto save(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Instance of Project to save"
            )
            ProjectCreateDto newProjectDto,

            @AuthenticationPrincipal
            @Parameter(hidden = true)
<<<<<<< HEAD
            AuthUserDetails principal
=======
            String email
>>>>>>> 260ae4d (Initial commit)
    );

    @Operation(summary = "Get Project", description = "Get one Project from the Database by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class))),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @GetMapping("/{id}")
    ProjectDto getById(
            @PathVariable
            @Parameter(required = true, description = "Project ID to search")
            String id
    );

    @Operation(summary = "Get all Projects", description = "Get all Projects from the Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Projects list",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class)))
    })
    @GetMapping
    List<ProjectDto> getAll();

    @Operation(summary = "Update Project", description = "Update existed Project in the Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Project fields values"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @PutMapping
    void update(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Instance of Project to update"
            )
            ProjectDto project
    );

    @Operation(summary = "Delete Project", description = "Delete Project from the Database by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @DeleteMapping("/{id}")
    void deleteById(
            @PathVariable
            @Parameter(required = true, description = "Project ID to delete")
            String id
    );
}
