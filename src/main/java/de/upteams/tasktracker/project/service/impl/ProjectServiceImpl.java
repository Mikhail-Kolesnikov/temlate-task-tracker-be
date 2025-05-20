package de.upteams.tasktracker.project.service.impl;

import de.upteams.tasktracker.project.dto.ProjectDto;
import de.upteams.tasktracker.project.dto.request.ProjectCreateDto;
import de.upteams.tasktracker.project.entity.Project;
import de.upteams.tasktracker.project.exception.ProjectNotFoundException;
import de.upteams.tasktracker.project.persistence.ProjectRepository;
import de.upteams.tasktracker.project.service.interfaces.ProjectService;
import de.upteams.tasktracker.project.utils.ProjectMapper;
import de.upteams.tasktracker.user.entity.AppUser;
<<<<<<< HEAD
=======
import de.upteams.tasktracker.user.service.UserService;
>>>>>>> 260ae4d (Initial commit)
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for various operations with Projects
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mappingService;
<<<<<<< HEAD

    @Override
    public ProjectDto save(ProjectCreateDto newProjectDto, AppUser projectOwner) {
        Project project = mappingService.mapDtoToEntity(newProjectDto);
        project.setOwner(projectOwner);
=======
    private final UserService userService;

    @Override
    public ProjectDto save(ProjectCreateDto newProjectDto, String email) {
        Project project = mappingService.mapDtoToEntity(newProjectDto);
        AppUser author = userService.getByEmailOrThrow(email);
        project.setOwner(author);
>>>>>>> 260ae4d (Initial commit)
        return mappingService.mapEntityToDto(repository.save(project));
    }

    @Override
    public ProjectDto getById(String id) {
        return mappingService.mapEntityToDto(getOrTrow(id));
    }

    @Override
    public Project getOrTrow(String id) {
        return repository
                .findById(UUID.fromString(id))
                .orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public List<ProjectDto> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mappingService::mapEntityToDto)
                .toList();
    }

    @Override
    @Transactional
    public void update(ProjectDto dto) {
        Project existedProject = getOrTrow(dto.getId());

        String newTitle = dto.getTitle();
        String newDescription = dto.getDescription();

        if (newTitle != null && !newTitle.trim().isEmpty()) {
            existedProject.setTitle(newTitle);
        }

        if (newDescription != null && !newDescription.trim().isEmpty()) {
            existedProject.setDescription(newDescription);
        }
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
