package com.volunteer.mapper;

import com.volunteer.pojo.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volunteer.vo.TeamVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Mapper
public interface TeamMapper extends BaseMapper<Team> {

    @Select("select * from team where CONCAT(team_name,community_createdate,serve_area,serve_type,team_id) like concat('%',#{keywords},'%')")
    List<Team> searchTeam(String keywords);

    @Select("select * from team where user_id = #{userId}")
    List<Team> getMyTeam(Long userId);
}
