
package de.upteams.tasktracker.project.controller.api;

import de.upteams.tasktracker.project.dto.ProjectDto;
import de.upteams.tasktracker.project.dto.request.ProjectCreateDto;
import de.upteams.tasktracker.security.service.AuthUserDetails;
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

// ✅ Добавлено: тег контроллера — используется в Swagger UI для группировки методов
@Tag(name = "Project controller", description = "Controller for various operations with Projects")
@RequestMapping("/api/v1/projects")
@PreAuthorize("isAuthenticated()")
public interface ProjectApi {

    // ✅ Описание метода создания проекта и возможных ответов
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

            // ✅ Не отображается в Swagger: авторизационный пользователь
            @AuthenticationPrincipal
            @Parameter(hidden = true)
            AuthUserDetails principal
    );

    // ✅ Описание метода получения проекта по ID
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

    // ✅ Описание метода получения всех проектов
    @Operation(summary = "Get all Projects", description = "Get all Projects from the Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Projects list",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class)))
    })
    @GetMapping
    List<ProjectDto> getAll();

    // ✅ Описание метода обновления проекта
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

    // ✅ Описание метода удаления проекта по ID
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
