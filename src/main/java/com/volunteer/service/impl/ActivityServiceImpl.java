package com.volunteer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.mapper.EnrollMapper;
import com.volunteer.pojo.Activity;
import com.volunteer.mapper.ActivityMapper;
import com.volunteer.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteer.util.ActivityUtil;
import com.volunteer.vo.ActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ActivityUtil activityUtil;

    @Autowired
    EnrollMapper enrollMapper;

    @Override
    public int registerActivity(ActivityVo activityVo) {
        return activityMapper.insert(activityUtil.vo2Activity(activityVo));
    }

    @Override
    public ActivityVo getActivityinfo(Long activityId) {
        ActivityVo activityVo = activityUtil.activity2Vo(activityMapper.selectById(activityId));
        if(enrollMapper.getActivityEnrollCount(activityId) >= activityVo.getRecruitNumber()){
            activityVo.setRecruitStatus(1);
        }
        return  activityVo;
    }

    @Override
    public int updateActivityInfo(ActivityVo activityVo){
        return activityMapper.updateById(activityUtil.vo2Activity(activityVo));
    }

    @Override
    public int deleteActivity(Long activityId) {
        return activityMapper.deleteById(activityId);
    }

    @Override
    public List<ActivityVo> getDefaultActivity() {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("start_datetime");
        return activityMapper.selectList(queryWrapper)
                .stream().map(a->activityUtil.activity2Vo(a)).collect(Collectors.toList())
                .stream().filter(activityVo->{
                    if(enrollMapper.getActivityEnrollCount(activityVo.getId()) >= activityVo.getRecruitNumber()){
                        activityVo.setRecruitStatus(1);
                    }
                    return true;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ActivityVo> searchActivity(String keyWords) {
        return activityMapper.search(keyWords)
                .stream().map(a->activityUtil.activity2Vo(a)).collect(Collectors.toList())
                .stream().filter(activityVo->{
                    if(enrollMapper.getActivityEnrollCount(activityVo.getId()) >= activityVo.getRecruitNumber()){
                        activityVo.setRecruitStatus(1);
                    }
                    return true;
                }).collect(Collectors.toList());
    }

    @Override
    public int auditActivity(Long activityId, Integer status) {
        Activity activity = activityMapper.selectById(activityId);
        activity.setStatus(status);
        return activityMapper.updateById(activity);
    }
}
