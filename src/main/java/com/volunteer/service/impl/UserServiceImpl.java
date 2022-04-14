package com.volunteer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.mapper.ActivityMapper;
import com.volunteer.mapper.EnrollMapper;
import com.volunteer.mapper.TeamMapper;
import com.volunteer.pojo.Activity;
import com.volunteer.pojo.Enroll;
import com.volunteer.pojo.User;
import com.volunteer.mapper.UserMapper;
import com.volunteer.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteer.util.CryptUtil;
import com.volunteer.util.JwtUtil;
import com.volunteer.util.TeamUtil;
import com.volunteer.util.UserUtil;
import com.volunteer.vo.ServeTime;
import com.volunteer.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wb
 * @since 2022-04-10
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    EnrollMapper enrollMapper;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    CryptUtil cryptUtil;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserUtil userUtil;

    @Autowired
    TeamUtil teamUtil;

    @Override
    public int registerUser(UserVo userVo) {
        User user = userUtil.vo2User(userVo);
        user.setPassword(cryptUtil.encode(userVo.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public Map<String, Object> login(String account, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        User user = userMapper.selectOne(wrapper);
        if(user!=null){
            if(cryptUtil.matches(password,user.getPassword())){
                Map<String,Object> res = new HashMap<>();
                String token = jwtUtil.generateToken(user.getId());
                UserVo userVo = userUtil.user2Vo(user);
                userVo.setTeamList(teamMapper.getMyTeam(userVo.getId()).stream().map(t->teamUtil.team2Vo(t)).collect(Collectors.toList()));
                res.put("userinfo",userVo);
                res.put("token",token);
                return res;
            }
        }
        return null;
    }

    @Override
    public UserVo getUserinfo(Long userId) {
        User user = userMapper.selectById(userId);
        UserVo userVo = userUtil.user2Vo(user);
        return userVo;
    }

    @Override
    public int updateUserInfo(UserVo userVo){
        /**
         * 不提供更改账号和密码
         */
        User dbUser = userMapper.selectById(userVo.getId());
        User user = userUtil.vo2User(userVo);
        user.setPassword(dbUser.getPassword());
        user.setAccount(dbUser.getAccount());
        return userMapper.updateById(user);
    }

    @Override
    public int deleteUser(Long userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public List<UserVo> getAll() {
        return userMapper.getAll()
                .stream().map(u->userUtil.user2Vo(u)).collect(Collectors.toList())
                .stream().filter(u-> {
                    u.setTeamList(teamMapper.getMyTeam(u.getId()).stream().map(t->teamUtil.team2Vo(t)).collect(Collectors.toList()) );
                    return true;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ServeTime> getMyServeTime() throws ClassNotFoundException, IllegalAccessException {
        User user = userUtil.getUser();
        return enrollMapper.getMyEnrolled(user.getId()).stream().map(e -> {
            Activity activity = activityMapper.selectById(e.getActivityId());
            return new ServeTime(activity.getName(), activity.getLength());
        }).collect(Collectors.toList());
    }
}
