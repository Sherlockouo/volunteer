package com.volunteer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.mapper.UserMapper;
import com.volunteer.pojo.Team;
import com.volunteer.mapper.TeamMapper;
import com.volunteer.pojo.User;
import com.volunteer.service.TeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteer.util.TeamUtil;
import com.volunteer.util.UserUtil;
import com.volunteer.vo.TeamVo;
import com.volunteer.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Slf4j
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserUtil userUtil;

    @Autowired
    TeamUtil teamUtil;

    @Override
    public int registerTeam(TeamVo teamVo) {
        Team team = new Team();
        User user = userMapper.getUserByAccount(teamVo.getAccount());
        if(user == null){
            log.info("account:{}, 不存在",teamVo.getAccount());
            return 0;
        }
        teamVo.setUserId(user.getId());
        return teamMapper.insert(teamUtil.vo2Team(teamVo));
    }

    @Override
    public TeamVo getTeaminfo(Long teamId) {
        Team team = teamMapper.selectById(teamId);

        TeamVo teamVo = teamUtil.team2Vo(team);
        teamVo.setUserVo(userUtil.user2Vo(userMapper.selectById(team.getUserId())));
        return teamVo;
    }

    @Override
    public int updateTeamInfo(TeamVo teamVo){
        Team team = teamMapper.selectById(teamVo.getTeamId());
        /**
         * 保证team创建者不被篡改
          */
        teamVo.setUserId(team.getUserId());
        return teamMapper.updateById(teamUtil.vo2Team(teamVo));
    }

    @Override
    public int deleteTeam(Long teamId) {
        return teamMapper.deleteById(teamId);
    }

    @Override
    public List<TeamVo> getTeamList() {
        return teamMapper.selectList(null).stream().map(t -> teamUtil.team2Vo(t)).collect(Collectors.toList())
                .stream().filter(teamVo -> {
                    UserVo userVo = userUtil.user2Vo(userMapper.selectById(teamVo.getUserId()));
                    teamVo.setUserVo(userVo);
                    teamVo.setAccount(userVo.getAccount());
                    return true;
                }).collect(Collectors.toList());

    }

    @Override
    public List<TeamVo> getMyTeamList() throws ClassNotFoundException, IllegalAccessException {
        User user = userUtil.getUser();
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        return teamMapper.selectList(queryWrapper)
                .stream()
                .map(t->teamUtil.team2Vo(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamVo> searchTeam(String keywords) {
        return teamMapper.searchTeam(keywords).stream().map(t -> teamUtil.team2Vo(t)).collect(Collectors.toList())
                .stream().filter(teamVo -> {
                    UserVo userVo = userUtil.user2Vo(userMapper.selectById(teamVo.getUserId()));
                    teamVo.setUserVo(userVo);
                    teamVo.setAccount(userVo.getAccount());
                    return true;
                }).collect(Collectors.toList());
    }

    @Override
    public int auditTeam(Long teamId, Integer status) {
        Team team = teamMapper.selectById(teamId);
        team.setStatus(status);
        return teamMapper.updateById(team);
    }

}
