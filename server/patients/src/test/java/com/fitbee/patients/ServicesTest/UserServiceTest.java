package com.fitbee.patients.ServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fitbee.patients.models.User;
import com.fitbee.patients.repositories.UserRepository;
import com.fitbee.patients.services.UserServiceImpl;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class UserServiceTest {

    @Autowired
    UserServiceImpl uSerServiceImpl;

    @MockBean
    UserRepository userRepository;

    @Test
    public void getUserIdByEmailTest(){
        User user= new User(1,"user1","pass1","user1@gmail.com",new Date(2022,12,12),1234567790,"patient");

        when(userRepository.findByEmail("user1@gmail.com")).thenReturn(user);
        long userId = uSerServiceImpl.getUserId("user1@gmail.com");
        assertEquals(1,userId);


    }

    @Test
    public void getSingleUserTest(){
        User user=new User(1,"user1","pass1","user1@gmail.com",new Date(2022,12,12),1234567790,"patient");

        when(userRepository.findByUserName("user1")).thenReturn(user);
        User user1= uSerServiceImpl.getSingleUser("user1");
        assertEquals("user1",user1.getUserName());
        assertEquals("pass1",user.getPassword());
        assertEquals("user1@gmail.com",user1.getEmail());
        assertEquals(new Date(2022,12,12),user1.getDateOfBirth());
        assertEquals(1234567790,user1.getPhoneNumber());
        assertEquals("patient",user1.getRole());
    }


}
