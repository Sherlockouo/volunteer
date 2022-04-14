package com.volunteer.mapper;

import com.volunteer.pojo.Enroll;
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
public interface EnrollMapper extends BaseMapper<Enroll> {

    @Select("select count(*) from enroll where activity_id = #{activityId}")
    int getActivityEnrollCount(Long activityId);

    @Select("select * from enroll where user_id = #{userId}")
    List<Enroll> getMyEnroll(Long userId);

    @Select("select * from enroll where user_id = #{userId} and enroll_status = 1")
    List<Enroll> getMyEnrolled(Long userId);

    @Select("select * from enroll where activity_id = #{activityId} and status = 1")
    List<Enroll> getActivityUser(Long activityId);
}
