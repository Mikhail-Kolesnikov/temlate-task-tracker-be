package de.upteams.tasktracker.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;


@Schema(name = "NewUser", description = "Registration details")
public record LoginRequest(
        @Schema(description = "user's email", example = "tes_dev@upteams.de")
        String email,

        @Schema(description = "user's password", example = "dev_TR_pass_007")
        @NotBlank String password) implements Serializable {
}
