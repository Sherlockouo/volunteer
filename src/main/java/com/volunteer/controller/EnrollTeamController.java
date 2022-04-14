package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.pojo.EnrollTeam;
import com.volunteer.service.EnrollService;
import com.volunteer.service.EnrollTeamService;
import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import com.volunteer.util.Role;
import com.volunteer.vo.TeamVo;
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
 * @since 2022-04-12
 */
@Api("加入团队接口")
@RestController
@RequestMapping("/enrollTeam")
public class EnrollTeamController {

    @Autowired
    EnrollTeamService enrollTeamService;

    @ApiOperation("加入团队")
    @PostMapping("/join")
    @HasRole(role = {Role.USER})
    public Result joinTeam(@RequestBody EnrollTeam enrollTeam){
        int res = enrollTeamService.joinTeam(enrollTeam);
        if(res > 0){
            return new Result(ResultEnum.SUCCESS_MESSAGE,null);
        }else{
            return new Result(ResultEnum.PARAM_ERROR,null);
        }
    }

    @ApiOperation("退出团队")
    @DeleteMapping("/quit")
    @HasRole(role = {Role.USER})
    public Result quitTeam(Long teamId) throws ClassNotFoundException, IllegalAccessException {
        int res = enrollTeamService.quitTeam(teamId);
        if(res > 0){
            return new Result(ResultEnum.SUCCESS_MESSAGE,null);
        }else{
            return new Result(ResultEnum.PARAM_ERROR,null);
        }
    }

    @ApiOperation("获取团队成员数目")
    @GetMapping("/membercount")
    public Result getTeamMembers(Long teamId) throws ClassNotFoundException, IllegalAccessException {
        int res = enrollTeamService.getMemberCount(teamId);
        if(res >=0){
            return new Result(ResultEnum.SUCCESS_MESSAGE,res);
        }else{
            return new Result(ResultEnum.PARAM_ERROR,null);
        }
    }

    @ApiOperation("获取我参加的团队")
    @GetMapping("/myteam")
    @HasRole(role = {Role.USER,Role.TEAM,Role.ADMIN})
    public Result getMyTeam() throws ClassNotFoundException, IllegalAccessException {
        List<TeamVo> res = enrollTeamService.userTeam();
        if(res != null){
            return new Result(ResultEnum.SUCCESS_MESSAGE,res);
        }else{
            return new Result(ResultEnum.PARAM_ERROR,null);
        }
    }



}
