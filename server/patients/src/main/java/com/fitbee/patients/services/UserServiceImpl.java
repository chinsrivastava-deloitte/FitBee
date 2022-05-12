package com.fitbee.patients.services;

import com.fitbee.patients.models.User;
import com.fitbee.patients.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public void addUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getSingleUser(String name){
        User user=userRepository.findByUserName((name));
        return user;
    }

    public long getUserId(String email){
        User user=userRepository.findByEmail(email);
        return user.getUserId();
    }

}

