package com.volunteer.util;

import com.volunteer.pojo.Team;
import com.volunteer.vo.TeamVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TeamUtil {
    
    public Team vo2Team(TeamVo teamVo){
        Team team = new Team();
        BeanUtils.copyProperties(teamVo,team);
        team.setServeArea(teamVo.getServeArea().toString());
        team.setServeType(teamVo.getServeType().toString());
        return team;
    }

    public TeamVo team2Vo(Team team){
        TeamVo teamVo = new TeamVo();
        BeanUtils.copyProperties(team,teamVo);
        teamVo.setServeArea(Arrays.asList(team.getServeArea().replace('[',' ').replace(']',' ').replaceAll(" ","").split(",")));
        teamVo.setServeType(Arrays.asList(team.getServeType().replace('[',' ').replace(']',' ').replaceAll(" ","").split(",")));
        return teamVo;
    }
}
