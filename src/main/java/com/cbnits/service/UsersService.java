package com.cbnits.service;

import com.cbnits.entity.Users;
import com.cbnits.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public boolean authenticate(String userName, String password) {
        log.info("Authenticating user: {}", userName);
        Optional<Users> user = usersRepository.findByUserName(userName);
        boolean isAuthenticated = user.isPresent() && user.get().getPassword().equals(password);

        if (isAuthenticated) {
            log.info("User {} authenticated successfully", userName);
        } else {
            log.warn("Failed authentication attempt for user: {}", userName);
        }
        return isAuthenticated;
    }

    public Users registerUser(Users users) {
        log.info("Attempting to register user: {}", users.getUserName());

        Optional<Users> existingUser = usersRepository.findByUserName(users.getUserName());
        if (existingUser.isPresent()) {
            log.warn("Registration failed: User {} already exists", users.getUserName());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with that username already exists");
        }

        Users savedUser = usersRepository.save(users);
        log.info("User registered successfully: {}", savedUser.getUserName());
        return savedUser;
    }
}
