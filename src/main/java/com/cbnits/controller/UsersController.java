package com.cbnits.controller;

import com.cbnits.dto.LoginRequest;
import com.cbnits.dto.LoginResponse;
import com.cbnits.dto.UserRequestDTO;
import com.cbnits.entity.Users;
import com.cbnits.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users APIs", description = "REST APIs for CBNITS Users")
@RestController
@RequestMapping("/user")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Operation(summary = "User registration REST API", description = "REST API to register users", tags = {"Users APIs"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Received request to register user: {}", userRequestDTO.getUserName());

        Users user = new Users();
        user.setUserName(userRequestDTO.getUserName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        Users registeredUser = usersService.registerUser(user);
        log.info("User registered successfully with username: {}", registeredUser.getUserName());
        return ResponseEntity.ok(registeredUser);
    }

    @Operation(summary = "User login REST API", description = "REST API to login for users", tags = {"Users APIs"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status SUCCESSFULLY"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Received login request for username: {}", loginRequest.getUserName());

        boolean isAuthenticated = usersService.authenticate(loginRequest.getUserName(), loginRequest.getPassword());

        if (isAuthenticated) {
            log.info("User {} authenticated successfully", loginRequest.getUserName());
            return ResponseEntity.ok(new LoginResponse("Login successful!", HttpStatus.OK.value()));
        } else {
            log.warn("Invalid login attempt for username: {}", loginRequest.getUserName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid username or password", HttpStatus.UNAUTHORIZED.value()));
        }
    }
}