package de.upteams.tasktracker.project.utils;

import de.upteams.tasktracker.project.dto.ProjectDto;
import de.upteams.tasktracker.project.dto.request.ProjectCreateDto;
import de.upteams.tasktracker.project.entity.Project;
import de.upteams.tasktracker.user.dto.EmployeeDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T11:47:44+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectDto mapEntityToDto(Project entity) {
        if ( entity == null ) {
            return null;
        }

        String title = null;
        String description = null;

        title = entity.getTitle();
        description = entity.getDescription();

        String id = null;
        EmployeeDto owner = null;

        ProjectDto projectDto = new ProjectDto( id, title, description, owner );

        return projectDto;
    }

    @Override
    public Project mapDtoToEntity(ProjectCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Project project = new Project();

        project.setTitle( dto.title() );
        project.setDescription( dto.description() );

        return project;
    }
}
