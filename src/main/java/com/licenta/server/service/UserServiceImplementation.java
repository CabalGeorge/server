package com.licenta.server.service;

import com.licenta.server.model.User;
import com.licenta.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {

        return userRepo.findAll();
    }

}
