package com.cbnits.service;

import com.cbnits.entity.Users;
import com.cbnits.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public boolean authenticate(String userName, String password) {
        Optional<Users> user = usersRepository.findByUserName(userName);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public Users registerUser(Users users) {

        Optional<Users> existingUser = usersRepository.findByUserName(users.getUserName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with that username already exists");
        }
        return usersRepository.save(users);
    }


}