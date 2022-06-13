package com.learning.springboot.service.impl;

import com.learning.springboot.common.Constants;
import com.learning.springboot.common.Token;
import com.learning.springboot.common.TokenDb;
import com.learning.springboot.dao.HogwartsTestUserMapper;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.dto.TokenDto;
import com.learning.springboot.entity.HogwartsTestUser;
import com.learning.springboot.service.HogwartsTestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HogwartsTestUserServiceImpl implements HogwartsTestUserService {

    @Autowired
    private HogwartsTestUserMapper hogwartsTestUserMapper;

    @Autowired
    private TokenDb tokenDb;

    @Override
    public ResultDto<Token> login(String userName, String password) {

        HogwartsTestUser queryHogwartsTestUser = new HogwartsTestUser();
        String encryptPwd = DigestUtils.md5DigestAsHex((Constants.md5Hex_sign + userName+password).getBytes());
        queryHogwartsTestUser.setUserName(userName);
        queryHogwartsTestUser.setPassword(encryptPwd);

        HogwartsTestUser resultHogwartsTestUser = hogwartsTestUserMapper.selectOne(queryHogwartsTestUser);

        if(Objects.isNull(resultHogwartsTestUser)){
            return ResultDto.resultFail("用户不存在或密码错误");
        }

        Token token = new Token();

        String tokenStr = DigestUtils.md5DigestAsHex((System.currentTimeMillis() + userName+password).getBytes());

        token.setToken(tokenStr);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setUserId(resultHogwartsTestUser.getId());
        tokenDto.setUserName(userName);
        tokenDto.setToken(tokenStr);
        tokenDto.setDefaultJenkinsId(resultHogwartsTestUser.getDefaultJenkinsId());

        tokenDb.addTokenDto(tokenStr, tokenDto);

        return ResultDto.resultSuccess("成功", token);
    }

    @Override
    public ResultDto<HogwartsTestUser> saveDB(HogwartsTestUser testUser) {

        HogwartsTestUser queryUser =  new HogwartsTestUser();
        queryUser.setUserName(testUser.getUserName());

        HogwartsTestUser userResult = hogwartsTestUserMapper.selectOne(queryUser);

        if(Objects.nonNull(userResult)){
            return ResultDto.resultFail("SaveDB failed,user already exists",testUser);
        }

        testUser.setCreateTime(new Date());
        testUser.setUpdateTime(new Date());

        String userName = testUser.getUserName();
        String password = testUser.getPassword();

        String encryptPwd = DigestUtils.md5DigestAsHex((Constants.md5Hex_sign + userName+password).getBytes());
        testUser.setPassword(encryptPwd);

        hogwartsTestUserMapper.insert(testUser);

        return ResultDto.resultSuccess("SaveDB success",testUser);

    }

    @Override
    public ResultDto<HogwartsTestUser> updateDB(HogwartsTestUser testUser) {

        testUser.setUpdateTime(new Date());
        //使用mybatis xml的方式
        //hogwartsTestUserMapper.updateUser(testUser.getUserName(), testUser.getPassword(), testUser.getId(), testUser.getEmail());
        hogwartsTestUserMapper.updateByPrimaryKeySelective(testUser);
        return ResultDto.resultSuccess("updateDB success", testUser);
    }

    @Override
    public ResultDto<List<HogwartsTestUser>> queryByName(HogwartsTestUser testUser) {

        //List<HogwartsTestUser> testUserList = hogwartsTestUserMapper.queryByName(testUser.getUserName(), testUser.getId());

        List<HogwartsTestUser> testUserList = hogwartsTestUserMapper.select(testUser);
        return ResultDto.resultSuccess("queryByName success",testUserList);
    }

    @Override
    public ResultDto deleteUserById(Integer userId) {

        HogwartsTestUser testUser = new HogwartsTestUser();
        testUser.setId(userId);
        hogwartsTestUserMapper.delete(testUser);
        return ResultDto.resultSuccess("deleteUserById success "+ userId);
    }


}
