package de.upteams.tasktracker.collaborator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import de.upteams.tasktracker.validation.ValidationConstants;
import java.util.Set;

@Entity
@Table(name = "collaborator")
public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "{collaborator.id.notNull}")
    private Long id;

    @NotNull(message = "{collaborator.userId.notNull}")
    @Column(name = "app_user_id")
    private Long appUserId;

    @NotNull(message = "{collaborator.projectId.notNull}")
    @Column(name = "project_id")
    private Long projectId;

    @NotNull(message = "{collaborator.roles.notNull}")
    @Size(max = ValidationConstants.COLLABORATOR_ROLES_MAX, message = "{collaborator.roles.size}")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "collaborator_roles", joinColumns = @JoinColumn(name = "collaborator_id"))
    @Enumerated(EnumType.STRING)
    private Set<ProjectRoles> projectRolesSet;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAppUserId() { return appUserId; }
    public void setAppUserId(Long appUserId) { this.appUserId = appUserId; }
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public Set<ProjectRoles> getProjectRolesSet() { return projectRolesSet; }
    public void setProjectRolesSet(Set<ProjectRoles> projectRolesSet) { this.projectRolesSet = projectRolesSet; }
}
