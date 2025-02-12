package com.cbnits.controller;

import com.cbnits.dto.LoginRequest;
import com.cbnits.dto.LoginResponse;
import com.cbnits.dto.UserRequestDTO;
import com.cbnits.entity.Users;
import com.cbnits.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = usersService.authenticate(loginRequest.getUserName(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok(new LoginResponse("Login successful!", HttpStatus.OK.value()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid username or password", HttpStatus.UNAUTHORIZED.value()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        // Convert DTO to Entity
        Users user = new Users();
        user.setUserName(userRequestDTO.getUserName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        Users registeredUser = usersService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

}
