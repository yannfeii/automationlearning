package com.learning.springboot.service.impl;

import com.learning.springboot.dto.testUser.TestUserDto;
import com.learning.springboot.service.TestUserService;
import org.springframework.stereotype.Service;

@Service
public class TestUserServiceImpl implements TestUserService {
    @Override
    public String login(TestUserDto testUserDto) {

        System.out.println("---testUserDto.getUser()------"+ testUserDto.getUserName());
        System.out.println("---testUserDto.getPwd()------"+ testUserDto.getPassword());

        return testUserDto.getUserName()+" - "+ testUserDto.getPassword();
    }

}
