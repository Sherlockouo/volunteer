package com.volunteer.util;

import com.volunteer.pojo.Activity;
import com.volunteer.vo.ActivityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ActivityUtil {

    public Activity vo2Activity(ActivityVo activityVo){
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityVo,activity);
        activity.setType(activityVo.getType().toString());
        return activity;
    }

    public ActivityVo activity2Vo(Activity activity){
        ActivityVo activityVo = new ActivityVo();
        BeanUtils.copyProperties(activity,activityVo);
        activityVo.setType(Arrays.asList(activity.getType().replace('[',' ').replace(']',' ').replaceAll(" ","").split(",")));
        return activityVo;
    }
}
