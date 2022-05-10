package com.fitbee.patients.repositories;

import com.fitbee.patients.models.CustomUserDetails;
import com.fitbee.patients.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository useRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=useRepository.findByEmail(email);
        //return CustomUserDetails.build(user);
        org.springframework.security.core.userdetails.User user1=new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
        return user1;
    }

}
