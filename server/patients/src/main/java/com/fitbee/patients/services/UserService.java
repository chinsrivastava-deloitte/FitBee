package com.fitbee.patients.services;

import com.fitbee.patients.models.User;

public interface UserService {

    public void addUser(User user);
    public User getSingleUser(String name);
}

