package de.upteams.tasktracker.project.service.interfaces;

import de.upteams.tasktracker.project.dto.ProjectDto;
import de.upteams.tasktracker.project.dto.request.ProjectCreateDto;
import de.upteams.tasktracker.project.entity.Project;
<<<<<<< HEAD
import de.upteams.tasktracker.user.entity.AppUser;
=======
>>>>>>> 260ae4d (Initial commit)

import java.util.List;

/**
 * Service for various operations with Projects
 */
public interface ProjectService {

<<<<<<< HEAD
    ProjectDto save(ProjectCreateDto newProjectDto, AppUser projectOwner);
=======
    ProjectDto save(ProjectCreateDto newProjectDto, String email);
>>>>>>> 260ae4d (Initial commit)

    ProjectDto getById(String id);

    Project getOrTrow(String id);

    List<ProjectDto> getAll();

    void update(ProjectDto project);

    void delete(String id);
}
