package com.volunteer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volunteer.pojo.User;
import com.volunteer.vo.ServeTime;
import com.volunteer.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdf
 * @since 2022-03-21
 */
public interface UserService extends IService<User> {

    int registerUser(UserVo userVo);

    Map<String, Object> login(String username, String password);

    UserVo getUserinfo(Long userId);

    int updateUserInfo(UserVo userVo);

    int deleteUser(Long userId);

    List<UserVo> getAll();

    List<ServeTime> getMyServeTime() throws ClassNotFoundException, IllegalAccessException;
}
