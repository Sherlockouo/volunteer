package com.volunteer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volunteer.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdf
 * @since 2022-03-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where account = #{account}")
    User getUserByAccount(String account);

    @Select("select * from user order by role_id")
    List<User> getAll();
}
