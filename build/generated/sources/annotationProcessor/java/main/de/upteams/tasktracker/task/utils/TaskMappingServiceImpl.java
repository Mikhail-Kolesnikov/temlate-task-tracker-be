package de.upteams.tasktracker.task.utils;

import de.upteams.tasktracker.collaborator.entity.Collaborator;
import de.upteams.tasktracker.project.dto.ProjectDto;
import de.upteams.tasktracker.task.dto.TaskDto;
import de.upteams.tasktracker.task.entity.Task;
import de.upteams.tasktracker.user.dto.EmployeeDto;
import de.upteams.tasktracker.user.dto.RoleDto;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T11:47:44+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class TaskMappingServiceImpl implements TaskMappingService {

    @Override
    public TaskDto mapEntityToDto(Task entity) {
        if ( entity == null ) {
            return null;
        }

        String title = null;
        String description = null;

        title = entity.getTitle();
        description = entity.getDescription();

        ProjectDto project = null;
        String id = null;

        TaskDto taskDto = new TaskDto( id, title, description, project );

        if ( taskDto.getExecutors() != null ) {
            Set<EmployeeDto> set = collaboratorSetToEmployeeDtoSet( entity.getExecutors() );
            if ( set != null ) {
                taskDto.getExecutors().addAll( set );
            }
        }

        return taskDto;
    }

    @Override
    public Task mapDtoToEntity(TaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( dto.getTitle() );
        task.setDescription( dto.getDescription() );
        if ( task.getExecutors() != null ) {
            Set<Collaborator> set = employeeDtoSetToCollaboratorSet( dto.getExecutors() );
            if ( set != null ) {
                task.getExecutors().addAll( set );
            }
        }

        return task;
    }

    protected EmployeeDto collaboratorToEmployeeDto(Collaborator collaborator) {
        if ( collaborator == null ) {
            return null;
        }

        String id = null;

        if ( collaborator.getId() != null ) {
            id = collaborator.getId().toString();
        }

        String name = null;
        String password = null;
        String email = null;
        String avatar = null;
        RoleDto roles = null;

        EmployeeDto employeeDto = new EmployeeDto( id, name, password, email, avatar, roles );

        return employeeDto;
    }

    protected Set<EmployeeDto> collaboratorSetToEmployeeDtoSet(Set<Collaborator> set) {
        if ( set == null ) {
            return null;
        }

        Set<EmployeeDto> set1 = new LinkedHashSet<EmployeeDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Collaborator collaborator : set ) {
            set1.add( collaboratorToEmployeeDto( collaborator ) );
        }

        return set1;
    }

    protected Collaborator employeeDtoToCollaborator(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Collaborator collaborator = new Collaborator();

        return collaborator;
    }

    protected Set<Collaborator> employeeDtoSetToCollaboratorSet(Set<EmployeeDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Collaborator> set1 = new LinkedHashSet<Collaborator>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EmployeeDto employeeDto : set ) {
            set1.add( employeeDtoToCollaborator( employeeDto ) );
        }

        return set1;
    }
}
