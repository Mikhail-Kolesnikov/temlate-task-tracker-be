package de.upteams.tasktracker.user.controller.interfaces;

import de.upteams.tasktracker.user.dto.request.UserCreateDto;
import de.upteams.tasktracker.user.dto.response.UserCreateResponseDto;
import de.upteams.tasktracker.user.dto.response.UserResponseDto;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface RegisterControllerApi {


    @PermitAll
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    UserCreateResponseDto register(
            @RequestBody
            @Valid
            UserCreateDto registerUser
    );

    @PermitAll
    @GetMapping("/confirm/{code}")
    UserResponseDto confirmRegistration(
            @PathVariable
            String code
    );
}
