package com.volunteer.service;

import com.volunteer.pojo.EnrollTeam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.volunteer.vo.TeamVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wb
 * @since 2022-04-12
 */
public interface EnrollTeamService extends IService<EnrollTeam> {

    int joinTeam(EnrollTeam enroll);

    int getMemberCount(Long teamId);

    int quitTeam(Long teamId) throws ClassNotFoundException, IllegalAccessException;

    List<TeamVo> userTeam() throws ClassNotFoundException, IllegalAccessException;
}
