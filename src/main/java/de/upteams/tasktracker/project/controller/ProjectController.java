package de.upteams.tasktracker.project.controller;

import de.upteams.tasktracker.project.controller.api.ProjectApi;
import de.upteams.tasktracker.project.dto.ProjectDto;
import de.upteams.tasktracker.project.dto.request.ProjectCreateDto;
import de.upteams.tasktracker.project.service.interfaces.ProjectService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller that receives http-requests for various operations with Projects
 */
@RestController
public class ProjectController implements ProjectApi {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Override
    public ProjectDto save(ProjectCreateDto newProjectDto, String email) {
        return service.save(newProjectDto, email);
    }

    @Override
    public ProjectDto getById(String id) {
        return service.getById(id);
    }

    @Override
    public List<ProjectDto> getAll() {
        return service.getAll();
    }

    @Override
    public void update(ProjectDto project) {
        service.update(project);
    }

    @Override
    public void deleteById(String id) {
        service.delete(id);
    }
}
