package com.learning.springboot.service;

import com.learning.springboot.common.Token;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.entity.HogwartsTestUser;

import java.util.List;

public interface HogwartsTestUserService {

    ResultDto<HogwartsTestUser> saveDB(HogwartsTestUser testUser);

    ResultDto<HogwartsTestUser> updateDB(HogwartsTestUser testUser);

    ResultDto<List<HogwartsTestUser>> queryByName(HogwartsTestUser testUser);

    ResultDto deleteUserById(Integer userId);

    ResultDto<Token> login(String userName,String password);
}
