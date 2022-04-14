package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.pojo.Enroll;
import com.volunteer.service.EnrollService;
import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import com.volunteer.util.Role;
import com.volunteer.vo.ActivityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Api("报名接口 还有细节差完善")
@RestController
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    EnrollService enrollService;

    @ApiOperation("活动报名")
    @PostMapping("/register")
    @HasRole(role = {Role.USER,Role.TEAM,Role.ADMIN})
    public Result register(@RequestBody Enroll enroll) {
        int res = enrollService.registerEnroll(enroll);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else if(res == -1) {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }else{
            return new Result(ResultEnum.PARAM_ERROR, null);

        }

    }

    @ApiOperation("更新报名信息")
    @PutMapping("/update")
    @HasRole(role = {Role.USER})
    public Result update(@RequestBody Enroll enroll) throws ClassNotFoundException, IllegalAccessException {
        int res = enrollService.updateEnrollInfo(enroll);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("删除报名")
    @PostMapping("/delete/{enrollId}")
    @HasRole(role = {Role.ADMIN,Role.USER})
    public Result delete(@PathVariable Long enrollId) {
        int res = enrollService.deleteEnroll(enrollId);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("获取报名信息")
    @GetMapping("/get/{enrollId}")
    public Result getUserinfo(@PathVariable Long enrollId) {
        Enroll enrollinfo = enrollService.getEnrollinfo(enrollId);
        if (enrollinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, enrollinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取自己活动的报名信息")
    @GetMapping("/my")
    @HasRole(role = {Role.USER,Role.TEAM,Role.ADMIN})
    public Result getMyEnroll() throws ClassNotFoundException, IllegalAccessException {
        List<ActivityVo> enrollinfo = enrollService.getMyEnroll();
        if (enrollinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, enrollinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("结算服务时长 管理员操作")
    @PutMapping("/count")
    @HasRole(role = {Role.ADMIN})
    public Result countServeTime(Long activityId){
        int res = enrollService.countServeTime(activityId);
        if(res > 0){
            return new Result(ResultEnum.SUCCESS_MESSAGE,null);
        }else{
            return new Result(ResultEnum.SERVER_HAVE_ERROR,null);
        }
    }
}
