package com.cbnits.service;

import com.cbnits.entity.Users;
import com.cbnits.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public boolean authenticate(String userName, String password) {
        Optional<Users> user = usersRepository.findByUserName(userName);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}