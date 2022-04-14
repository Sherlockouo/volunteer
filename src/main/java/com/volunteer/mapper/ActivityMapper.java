package com.volunteer.mapper;

import com.volunteer.pojo.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("select * from activity where CONCAT(team_id,`name`,`desc`,address,start_datetime,end_datetime,recruit_status) like concat('%',#{keywords},'%')")
    List<Activity> search(String keyWords);
}
