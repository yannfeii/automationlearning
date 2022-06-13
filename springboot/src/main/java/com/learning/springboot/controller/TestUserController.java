package com.learning.springboot.controller;

import com.learning.springboot.common.ServiceException;
import com.learning.springboot.dao.HogwartsTestUserMapper;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.dto.testUser.TestUserDto;
import com.learning.springboot.entity.HogwartsTestUser;
import com.learning.springboot.service.TestUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Api(tags="TestUserController - Test Task Arrangement")
@RestController
@RequestMapping("testUserTest")
@Slf4j
public class TestUserController {

    @Autowired
    private TestUserService testUserService;

    @Value("${testUSer.key1}")
    private String testUSerKey1;
    @Autowired
    private HogwartsTestUserMapper hogwartsTestUserMapper;

    //@RequestMapping(value = "login",method = RequestMethod.GET)
    //@GetMapping()//http://localhost:8080/
    @GetMapping("login")
    public String loginGet(){
        return "loginGet Success";
    }

    @ApiOperation("login")
    @PostMapping("login")
    public ResultDto login(@RequestBody TestUserDto testUserDto){

        if(testUserDto.getUserName().contains("error1")){
            ServiceException.throwEx("user name contains error");
        }

        if(testUserDto.getUserName().contains("error2")){
            //throw new ServiceException("user name contains error2",new NullPointerException());
            throw new NullPointerException();
        }

        //String result = testUserService.login(testUserDto);

        HogwartsTestUser userQuery =  new HogwartsTestUser();
        userQuery.setUserName(testUserDto.getUserName());
        userQuery.setPassword(testUserDto.getPassword());

        HogwartsTestUser queryResult = hogwartsTestUserMapper.selectOne(userQuery);

        if(Objects.isNull(queryResult)){
            return ResultDto.resultFail("User does not exists");
        }

        TestUserDto userTmp = new TestUserDto();
        userTmp.setUserName(testUserDto.getUserName());
        userTmp.setToken(queryResult.getId()+"");


        //return "loginGet Success" + result + " testUSerKey1 = "+testUSerKey1;
       // return ResultDto.resultSuccess("成功"+ result + " testUSerKey1 = "+testUSerKey1,userTmp);
        return ResultDto.resultSuccess("User exists ",userTmp);
    }

    //@RequestMapping(value = "login",method = RequestMethod.POST)
    @PostMapping("login2")
    public ResultDto loginPost(@RequestBody TestUserDto testUserDto){

        System.out.println("---testUserDto.getUser()------"+ testUserDto.getUserName());
        System.out.println("---testUserDto.getPwd()------"+ testUserDto.getPassword());
        return ResultDto.resultSuccess("loginPost Success", testUserDto);
    }

    @RequestMapping(value = "byId/{userId}/{id}",method = RequestMethod.GET)
    public ResultDto getById(@PathVariable("userId") Long userId,@PathVariable("id") Long id){
        System.out.println("---userId------"+userId);
        System.out.println("---id------"+id);
        return ResultDto.resultSuccess("byId Success " +userId+"---id------"+id);
    }

    @GetMapping("byId")//http://localhost:8080/testUser/byId?userId=12&id=13
    public ResultDto getParam(@RequestParam("userId") Long userId,@RequestParam("id") Long id){
        System.out.println("---userId------"+userId);
        System.out.println("---id------"+id);
        return ResultDto.resultSuccess("getParam Success " +userId+"---id------"+id);
    }

    @GetMapping("byIds")//http://localhost:8080/testUser/byIds?id=13
    public ResultDto getParamDefault(@RequestParam(value = "userId",required = false) Long userId,@RequestParam("id") Long id){
        System.out.println("---userId------"+userId);
        System.out.println("---id------"+id);
        return ResultDto.resultSuccess("getParam Success " +userId+"---id------"+id);
    }

}
