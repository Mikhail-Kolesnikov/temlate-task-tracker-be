package de.upteams.tasktracker.task.entity;

import de.upteams.tasktracker.collaborator.entity.Collaborator;
import de.upteams.tasktracker.project.entity.Project;
import de.upteams.tasktracker.utils.BaseEntity;
import de.upteams.tasktracker.validation.ValidationConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static de.upteams.tasktracker.utils.EntityUtil.getIdForToString;
import static de.upteams.tasktracker.utils.EntityUtil.getIdsForToString;

/**
 * Task entity
 */
@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity {

    @NotBlank(message = "{task.title.notBlank}")
    @Size(
            min = ValidationConstants.TASK_TITLE_MIN_LENGTH,
            max = ValidationConstants.TASK_TITLE_MAX_LENGTH,
            message = "{task.title.size}"
    )
    @Pattern(
            regexp = ValidationConstants.TASK_TITLE_REGEX,
            message = "{task.title.pattern}"
    )
    @Column(name = "title", nullable = false)
    private String title;

    @Size(
            max = ValidationConstants.TASK_DESCRIPTION_MAX_LENGTH,
            message = "{task.description.size}"
    )
    @Pattern(
            regexp = ValidationConstants.TASK_DESCRIPTION_REGEX,
            message = "{task.description.pattern}"
    )
    @Column(name = "description")
    private String description;

    @NotNull(message = "{task.project.notNull}")
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final Set<Collaborator> executors = new HashSet<>();

    public Task(String title, String description, Project project) {
        this.title = title;
        this.description = description;
        this.project = project;
    }

    public Task(String title, Project project) {
        this.title = title;
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", executorsIds=" + getIdsForToString(executors) +
                ", projectId=" + getIdForToString(project) +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}