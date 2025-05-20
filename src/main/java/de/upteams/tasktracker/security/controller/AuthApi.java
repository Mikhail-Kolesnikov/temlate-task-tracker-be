package de.upteams.tasktracker.security.controller;

import de.upteams.tasktracker.security.dto.LoginRequest;
import de.upteams.tasktracker.security.entities.RefreshRequestDto;
import de.upteams.tasktracker.security.entities.TokenResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Authorization API description for Swagger
 */
@Tag(name = "Authorization controller", description = "Controller for User authorization")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @Operation(summary = "Login", description = "User login process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorization successfully completed"),
            @ApiResponse(responseCode = "401", description = "Incorrect User password or registration is not confirmed"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    TokenResponseDto login(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Instance of User with name and password"
            )
            @Valid
            LoginRequest loginRequest,
            HttpServletResponse response
    );

    @Operation(summary = "Get new access token", description = "Getting new access token by refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New access token granted"),
            @ApiResponse(responseCode = "401", description = "Invalid User refresh token")
    })
    @PostMapping("/refresh-token")
    TokenResponseDto refreshAccessToken(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Request that contains refresh token"
            )
            RefreshRequestDto request,
            HttpServletResponse response
    );

    @Operation(summary = "Logout", description = "User logout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout successful")
    })
    @PostMapping("/logout")
    TokenResponseDto logout(HttpServletResponse response);
}
