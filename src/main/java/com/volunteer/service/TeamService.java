package com.volunteer.service;

import com.volunteer.pojo.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.volunteer.vo.TeamVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
public interface TeamService extends IService<Team> {
    int registerTeam(TeamVo teamVo );

    TeamVo getTeaminfo(Long teamId);

    int updateTeamInfo(TeamVo teamVo) ;

    int deleteTeam(Long teamId);

    List<TeamVo> getTeamList();

    List<TeamVo> getMyTeamList() throws ClassNotFoundException, IllegalAccessException;

    List<TeamVo> searchTeam(String keywords);

    int auditTeam(Long teamId, Integer status);
}
