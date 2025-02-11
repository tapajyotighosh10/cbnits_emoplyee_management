package com.cbnits.controller;

import com.cbnits.dto.LoginRequest;
import com.cbnits.dto.LoginResponse;
import com.cbnits.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
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
}
