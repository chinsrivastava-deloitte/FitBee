package com.fitbee.patients.repositories;

import com.fitbee.patients.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}

