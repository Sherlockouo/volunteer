package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.service.TeamService;
import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import com.volunteer.util.Role;
import com.volunteer.vo.TeamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api("团队接口")
@RestController
@Slf4j
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;


    @ApiOperation("注册团队")
    @PostMapping("/register")
    @HasRole(role = {Role.USER,Role.TEAM,Role.ADMIN})
    public Result register(@RequestBody TeamVo teamVo) {
        int res = teamService.registerTeam(teamVo);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("更新团队信息")
    @PutMapping("/update")
    @HasRole(role = {Role.USER,Role.TEAM,Role.ADMIN})
    public Result update(@RequestBody TeamVo teamVo) {
        int res = teamService.updateTeamInfo(teamVo);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("管理员审核团队 传teamId 和 status")
    @PutMapping("/audit")
    @HasRole(role = {Role.ADMIN})
    public Result update(@RequestBody Map<String,Object> params) {
        int res = teamService.auditTeam(Long.parseLong(params.get("teamId").toString()),Integer.parseInt(params.get("status").toString()));
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("按Id删除团队")
    @DeleteMapping("/delete/{teamId}")
    @HasRole(role = {Role.ADMIN,Role.TEAM})
    public Result delete(@PathVariable Long teamId) {
        int res = teamService.deleteTeam(teamId);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("获取单个团队信息")
    @GetMapping("/get/{teamId}")
    public Result getUserinfo(@PathVariable Long teamId) {
        TeamVo teaminfo = teamService.getTeaminfo(teamId);
        if (teaminfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, teaminfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取我创建的团队")
    @GetMapping("/myteam")
    @HasRole(role = {Role.TEAM,Role.ADMIN})
    public Result getMyTeamList() throws ClassNotFoundException, IllegalAccessException {
        List<TeamVo> teaminfo = teamService.getMyTeamList();;
        if (teaminfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, teaminfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取默认团队列表")
    @GetMapping("/list")
    public Result getTeamList() {
        List<TeamVo> teaminfo = teamService.getTeamList();;
        if (teaminfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, teaminfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("搜索团队")
    @GetMapping("/search")
    public Result getTeamList(String keywords) {
        List<TeamVo> teaminfo = teamService.searchTeam(keywords);
        if (teaminfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, teaminfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }
}
