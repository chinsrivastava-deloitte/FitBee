package com.fitbee.patients.controllers;

import com.fitbee.patients.config.JwtUtils;
import com.fitbee.patients.models.Jwt.JwtRequest;
import com.fitbee.patients.models.Jwt.JwtResponse;
import com.fitbee.patients.models.User;
import com.fitbee.patients.repositories.CustomUserDetailsService;
import com.fitbee.patients.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String hello(){
        return "Hello";
    }

    @PostMapping(value="/add")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }
    @RequestMapping(value="/get/{name}",method=RequestMethod.GET)
    public User getone(@PathVariable String name){
        User user=userRepository.findByUserName(name);
        return user;
    }

}
