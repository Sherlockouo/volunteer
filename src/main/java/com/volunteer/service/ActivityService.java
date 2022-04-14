package com.volunteer.service;

import com.volunteer.pojo.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.volunteer.vo.ActivityVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
public interface ActivityService extends IService<Activity> {

    int registerActivity(ActivityVo activityVo);

    ActivityVo getActivityinfo(Long activityId);

    int updateActivityInfo(ActivityVo activityVo);

    int deleteActivity(Long activityId);

    List<ActivityVo> getDefaultActivity();

    List<ActivityVo> searchActivity(String keyWords);

    int auditActivity(Long activityId, Integer status);
}
