package com.volunteer.util;

import com.volunteer.mapper.UserMapper;
import com.volunteer.pojo.User;
import com.volunteer.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class UserUtil {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * 通过用户 请求token
     * @return
     * @throws ClassNotFoundException
     */
    public User getUser() throws ClassNotFoundException, IllegalAccessException {
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        String token=request.getHeader("Authorization");
        if(token == null){
            throw new IllegalAccessException("非法访问");
        }else{
            return   userMapper.selectById(jwtUtil.getUserId().asLong());
        }
    }

    public User vo2User(UserVo uservo){
        User user = new User();
        BeanUtils.copyProperties(uservo,user);
        user.setServeArea(uservo.getServeArea().toString());
        user.setServeType(uservo.getServeType().toString());
        return user;
    }

    public UserVo user2Vo(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setServeArea(Arrays.asList(user.getServeArea().replace('[',' ').replace(']',' ').replaceAll(" ","").split(",")));
        userVo.setServeType(Arrays.asList(user.getServeType().replace('[',' ').replace(']',' ').replaceAll(" ","").split(",")));
        return userVo;
    }



}
