package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.pojo.User;
import com.volunteer.service.UserService;
import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import com.volunteer.util.Role;
import com.volunteer.vo.ServeTime;
import com.volunteer.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wb
 * @since 2022-04-10
 */
@Api("用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserVo userVO) {
        Map<String, Object> res = userService.login(userVO.getAccount(), userVO.getPassword());
        if (res.size() == 2) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, res);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("注册管理员")
    @PostMapping("/regadmin")
    public Result registerAdmin(@RequestBody UserVo user) {
        user.setRoleId(3L);
        int res = userService.registerUser(user);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserVo user) {
        log.info("user:{}", user.toString());
        int res = userService.registerUser(user);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    @HasRole(role = {Role.USER})
    public Result update(@RequestBody UserVo userVo){
        int res = userService.updateUserInfo(userVo);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("获取用户信息")
    @GetMapping("/get/{userId}")
    public Result getUserinfo(@PathVariable Long userId) {
        UserVo userinfo = userService.getUserinfo(userId);
        if (userinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, userinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/delete/{userId}")
    @HasRole(role = {Role.ADMIN})
    public Result delete(@PathVariable Long userId) {
        int res = userService.deleteUser(userId);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("获取所有用户信息")
    @GetMapping("/all")
    @HasRole(role = {Role.ADMIN})
    public Result getAllUser(){
        List<UserVo> res = userService.getAll();
        if (res.size() >= 0)  {
            return new Result(ResultEnum.SUCCESS_MESSAGE, res);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取自己的服务时长")
    @GetMapping("/myserve")
    @HasRole(role = {Role.USER,Role.TEAM,Role.ADMIN})
    public Result getMyServeTime() throws ClassNotFoundException, IllegalAccessException {
        List<ServeTime> res = userService.getMyServeTime();
        if (res.size() >= 0)  {
            return new Result(ResultEnum.SUCCESS_MESSAGE, res);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }
}
