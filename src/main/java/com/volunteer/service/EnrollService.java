package com.volunteer.service;

import com.volunteer.pojo.Enroll;
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
public interface EnrollService extends IService<Enroll> {

    int registerEnroll(Enroll enroll);

    Enroll getEnrollinfo(Long enrollId);

    int updateEnrollInfo(Enroll enroll);

    int deleteEnroll(Long enrollId);

    List<ActivityVo> getMyEnroll() throws ClassNotFoundException, IllegalAccessException;

    int countServeTime(Long activityId);
}
