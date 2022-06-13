package com.learning.springboot.dao;

import com.learning.springboot.common.MySqlExtensionMapper;
import com.learning.springboot.entity.HogwartsTestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HogwartsTestUserMapper extends MySqlExtensionMapper<HogwartsTestUser> {

    int updateUser(@Param("userName") String userName, @Param("passWord") String passWord, @Param("userId") Integer userId,  @Param("email") String email);

    List<HogwartsTestUser> queryByName(@Param("userName") String userName, @Param("userId") Integer userId);
}