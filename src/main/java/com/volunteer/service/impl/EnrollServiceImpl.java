package com.volunteer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.mapper.ActivityMapper;
import com.volunteer.mapper.UserMapper;
import com.volunteer.pojo.Activity;
import com.volunteer.pojo.Enroll;
import com.volunteer.mapper.EnrollMapper;
import com.volunteer.pojo.User;
import com.volunteer.service.EnrollService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteer.util.ActivityUtil;
import com.volunteer.util.UserUtil;
import com.volunteer.vo.ActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Service
public class EnrollServiceImpl extends ServiceImpl<EnrollMapper, Enroll> implements EnrollService {

    @Autowired
    EnrollMapper enrollMapper;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    UserUtil userUtil;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ActivityUtil activityUtil;

    @Override
    public int registerEnroll(Enroll enroll) {
        Long activityId = enroll.getActivityId();
        Activity activity = activityMapper.selectById(activityId);
        int activityEnrollCount = enrollMapper.getActivityEnrollCount(activityId);
        if(activity.getRecruitNumber() <= activityEnrollCount){
            return  -1;
        }
        return enrollMapper.insert(enroll);
    }

    @Override
    public Enroll getEnrollinfo(Long enrollId) {
        return enrollMapper.selectById(enrollId);
    }

    @Override
    public int updateEnrollInfo(Enroll enroll) {
        return enrollMapper.updateById(enroll);
    }

    @Override
    public int deleteEnroll(Long enrollId) {
        return enrollMapper.deleteById(enrollId);
    }

    @Override
    public List<ActivityVo> getMyEnroll() throws ClassNotFoundException, IllegalAccessException {
        Long userId = userUtil.getUser().getId();

        return enrollMapper.getMyEnroll(userId)
                .stream()
                .map(a->activityUtil.activity2Vo(activityMapper.selectById(a.getActivityId())))
                .collect(Collectors.toList())
                .stream().filter(a->{
                    QueryWrapper<Enroll> enrollQueryWrapper = new QueryWrapper<>();
                    enrollQueryWrapper.eq("user_id",userId)
                                    .eq("activity_id",a.getId());
                    a.setStatus(enrollMapper.selectOne(enrollQueryWrapper).getEnrollStatus());
                    return true;
                }).collect(Collectors.toList());

    }

    @Override
    public int countServeTime(Long activityId) {
        try{
        BigDecimal length = activityMapper.selectById(activityId).getLength();
        enrollMapper.getActivityUser(activityId).stream().filter(a->{
            User user = userMapper.selectById(a.getUserId());
            user.setServeTime(user.getServeTime().add(new BigDecimal(length.toString())));
            userMapper.updateById(user);
            return true;
        });
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
        return 1;
    }
}
