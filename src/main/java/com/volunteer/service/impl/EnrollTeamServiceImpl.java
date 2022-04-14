package com.volunteer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.mapper.EnrollMapper;
import com.volunteer.mapper.TeamMapper;
import com.volunteer.mapper.UserMapper;
import com.volunteer.pojo.Enroll;
import com.volunteer.pojo.EnrollTeam;
import com.volunteer.mapper.EnrollTeamMapper;
import com.volunteer.service.EnrollTeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteer.util.TeamUtil;
import com.volunteer.util.UserUtil;
import com.volunteer.vo.TeamVo;
import com.volunteer.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wb
 * @since 2022-04-12
 */
@Service
public class EnrollTeamServiceImpl extends ServiceImpl<EnrollTeamMapper, EnrollTeam> implements EnrollTeamService {

    @Autowired
    EnrollTeamMapper enrollTeamMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserUtil userUtil;

    @Autowired
    TeamUtil teamUtil;

    @Autowired
    EnrollMapper enrollMapper;

    @Override
    public int joinTeam(EnrollTeam enrollTeam) {
        return enrollTeamMapper.insert(enrollTeam);
    }

    @Override
    public int getMemberCount(Long teamId) {
        return enrollTeamMapper.getMemberCount(teamId);
    }

    @Override
    public int quitTeam(Long teamId) throws ClassNotFoundException, IllegalAccessException {
        QueryWrapper<EnrollTeam> wrapper = new QueryWrapper<>();
        wrapper.eq("team_id", teamId)
                .eq("user_id", userUtil.getUser().getId());
        return enrollTeamMapper.delete(wrapper);
    }

    @Override
    public List<TeamVo> userTeam() throws ClassNotFoundException, IllegalAccessException {
        QueryWrapper<EnrollTeam> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userUtil.getUser().getId());
        return enrollTeamMapper.selectList(wrapper)
                .stream().map(e->teamMapper.selectById(e.getTeamId()))
                .collect(Collectors.toList())
                .stream().map(t -> teamUtil.team2Vo(t)).collect(Collectors.toList())
                .stream().filter(teamVo -> {
                    UserVo userVo = userUtil.user2Vo(userMapper.selectById(teamVo.getUserId()));
                    teamVo.setUserVo(userVo);
                    teamVo.setAccount(userVo.getAccount());
                    return true;
                }).collect(Collectors.toList());

    }
}
