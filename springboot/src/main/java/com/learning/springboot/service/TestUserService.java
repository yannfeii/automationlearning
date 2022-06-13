package com.learning.springboot.service;

import com.learning.springboot.dto.testUser.TestUserDto;

public interface TestUserService {
    
    public String login(TestUserDto testUserDto);
}
