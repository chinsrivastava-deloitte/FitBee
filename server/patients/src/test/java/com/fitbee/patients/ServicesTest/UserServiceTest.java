package com.fitbee.patients.ServicesTest;

import com.fitbee.patients.repositories.UserRepository;
import com.fitbee.patients.services.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceTest {

    @InjectMocks
    UserServiceImpl uSerServiceImpl;

    @Mock
    UserRepository userRepository;


}
