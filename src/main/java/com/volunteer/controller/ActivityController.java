package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.pojo.Activity;
import com.volunteer.service.ActivityService;
import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import com.volunteer.util.Role;
import com.volunteer.vo.ActivityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Api("活动接口")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @ApiOperation("注册活动")
    @PostMapping("/register")
    @HasRole(role = {Role.TEAM,Role.ADMIN})
    public Result register(@RequestBody ActivityVo activityVo) {
        int res = activityService.registerActivity(activityVo);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("更新活动信息")
    @PutMapping("/update")
    @HasRole(role = {Role.USER,Role.TEAM,Role.ADMIN})
    public Result update(@RequestBody ActivityVo activityVo){
        int res = activityService.updateActivityInfo(activityVo);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("更新活动信息")
    @PutMapping("/audit")
    @HasRole(role = {Role.ADMIN})
    public Result audit(@RequestBody Map<String,Object> parmas){
        int res = activityService.auditActivity(Long.parseLong(parmas.get("activityId").toString()),Integer.parseInt(parmas.get("status").toString()));
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("删除活动")
    @DeleteMapping("/delete/{activityId}")
    @HasRole(role = {Role.ADMIN})
    public Result delete(@PathVariable Long activityId) {
        int res = activityService.deleteActivity(activityId);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("获取活动信息")
    @GetMapping("/get/{activityId}")
    public Result getUserinfo(@PathVariable Long activityId) {
        ActivityVo activityinfo = activityService.getActivityinfo(activityId);
        if (activityinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, activityinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }


    @ApiOperation("获取默认活动")
    @GetMapping("/list")
    public Result getActivityList() {
        List<ActivityVo> defaultActivity = activityService.getDefaultActivity();
        if (defaultActivity != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, defaultActivity);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("搜索活动")
    @GetMapping("/search")
    public Result searchActivityList(String keywords) {
        List<ActivityVo> activities = activityService.searchActivity(keywords);

        if (activities != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, activities);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }
}
