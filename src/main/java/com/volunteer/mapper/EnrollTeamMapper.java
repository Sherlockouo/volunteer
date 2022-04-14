package com.volunteer.mapper;

import com.volunteer.pojo.EnrollTeam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wb
 * @since 2022-04-12
 */
@Mapper
public interface EnrollTeamMapper extends BaseMapper<EnrollTeam> {

    @Select("select count(*) from enroll_team where team_id = #{teamId}")
    int getMemberCount(Long teamId);
}
