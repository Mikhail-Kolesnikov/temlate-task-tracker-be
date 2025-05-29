
package de.upteams.tasktracker.security.controller;

import de.upteams.tasktracker.security.dto.LoginRequest;
import de.upteams.tasktracker.security.entities.RefreshRequestDto;
import de.upteams.tasktracker.security.entities.TokenResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Authorization API description for Swagger
 */
@Tag(name = "Authorization controller", description = "Controller for User authorization")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

    // ✅ Добавлено: описание метода входа в систему (login) и возможных ответов
    @Operation(summary = "Login", description = "User login process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorization successfully completed"),
            @ApiResponse(responseCode = "401", description = "Incorrect User password or registration is not confirmed"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    TokenResponseDto login(
            // ✅ Описание тела запроса для Swagger
            @RequestBody(
                    required = true,
                    description = "Instance of User with name and password"
            )
            @Valid
            LoginRequest loginRequest,

            // ✅ Скрыт из Swagger UI — технический параметр
            @Parameter(hidden = true)
            HttpServletResponse response
    );

    // ✅ Метод получения нового access токена по refresh токену
    @Operation(summary = "Get new access token", description = "Getting new access token by refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New access token granted"),
            @ApiResponse(responseCode = "401", description = "Refresh token is invalid or expired")
    })
    @PostMapping("/refresh")
    TokenResponseDto refresh(
            // ✅ Описание тела запроса для Swagger
            @RequestBody(
                    required = true,
                    description = "Refresh token instance"
            )
            RefreshRequestDto refreshRequest
    );
}
