package com.learning.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.learning.springboot.common.Constants;
import com.learning.springboot.common.Token;
import com.learning.springboot.common.TokenDb;
import com.learning.springboot.dto.TokenDto;
import com.learning.springboot.dto.testUser.HogwartsTestUserDto;
import com.learning.springboot.dto.jenkins.JenkinsDto;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.entity.HogwartsTestUser;
import com.learning.springboot.service.HogwartsTestUserService;
import com.learning.springboot.util.JenkinsUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@ApiModel(value = "用户管理")
@RequestMapping("testUser")
@RestController
@Slf4j
public class HogwartsTestUserController {

    @Autowired
    private HogwartsTestUserService hogwartsTestUserService;

    @Autowired
    private TokenDb tokenDb;

    @ApiOperation("用户注册")
    @PostMapping("register")
    public ResultDto userRegister(@RequestBody HogwartsTestUserDto testUserDto){

        HogwartsTestUser testUser = new HogwartsTestUser();
        BeanUtils.copyProperties(testUserDto,testUser);

        if(StringUtil.isEmpty(testUserDto.getPassword())){
            return ResultDto.resultFail("密码不能为空");
        }
        log.info(JSONObject.toJSONString(testUser));

        return hogwartsTestUserService.saveDB(testUser);
    }

    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public ResultDto<Token> login(@RequestBody HogwartsTestUserDto testUserDto) {

        log.info("用户登录-入参= "+ JSONObject.toJSONString(testUserDto));
        /*if(true){
            return ResultDto.fail("演示环境暂时不对外开放");
        }*/

        String userName = testUserDto.getUserName();
        String password = testUserDto.getPassword();
        log.info("userName= "+userName);
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            return ResultDto.resultFail("用户名或密码不能为空");
        }

        ResultDto<Token> resultDto = hogwartsTestUserService.login(userName, password);

        return resultDto;
    }

    @ApiOperation(value = "登出接口")
    @DeleteMapping("/logout")
    public ResultDto logout(HttpServletRequest request) {

        String token = request.getHeader(Constants.LOGIN_TOKEN);
        boolean loginFlag = tokenDb.isLogin(token);

        if(!loginFlag){
            return ResultDto.resultFail("用户未登录，无需退出");
        }

        TokenDto tokenDto = tokenDb.removeTokenDto(token);

        return ResultDto.resultSuccess("成功",tokenDto);
    }

    @ApiOperation(value = "是否已经登录接口")
    @GetMapping("/isLogin")
    public ResultDto<TokenDto> isLogin(HttpServletRequest request) {

        String token = request.getHeader(Constants.LOGIN_TOKEN);

        boolean loginFlag = tokenDb.isLogin(token);

        TokenDto tokenDto = tokenDb.getTokenDto(token);

        return ResultDto.resultSuccess("成功",tokenDto);
    }

    @ApiOperation("根据用户名模糊查询")
    @GetMapping("byName")
    public ResultDto<List<HogwartsTestUser>> userQueryByName(@RequestParam(value = "userId",required = false) Integer userId,@RequestParam(value = "userName", required = false) String userName){


        HogwartsTestUser testUser = new HogwartsTestUser();
        testUser.setId(userId);
        testUser.setUserName(userName);

        log.info("根据用户名模糊查询： "+JSONObject.toJSONString(testUser));
        return hogwartsTestUserService.queryByName(testUser);
    }

    @ApiOperation("根据ID删除用户")
    @DeleteMapping("{userId}")
    public ResultDto deleteUser(@PathVariable("userId") Integer userId){
        //http://localhost:8080/testUser/13
        log.info("根据ID删除用户： "+userId);
        return hogwartsTestUserService.deleteUserById(userId);
    }

    @ApiOperation("调用Jenkins创建Job")
    @PutMapping("build")
    public ResultDto jenkinsJob(@RequestBody JenkinsDto jenkinsDto) throws IOException, URISyntaxException {

        log.info("调用Jenkins创建Job： "+jenkinsDto);
        return JenkinsUtil.jenkinsBuild(jenkinsDto.getJobName(),jenkinsDto.getUserID(),jenkinsDto.getRemark(),jenkinsDto.getCommand());
    }

}
