package com.volunteer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.pojo.Report;
import com.volunteer.mapper.ReportMapper;
import com.volunteer.pojo.User;
import com.volunteer.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteer.util.UserUtil;
import com.volunteer.vo.ReportAuditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    UserUtil userUtil;

    @Override
    public int registerReport(Report report) {
        return reportMapper.insert(report);
    }

    @Override
    public Report getReportinfo(Long reportId) {
        return reportMapper.selectById(reportId);
    }

    @Override
    public List<Report> getUserReportinfo(Long userId){
        QueryWrapper<Report> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return reportMapper.selectList(wrapper);
    }

    @Override
    public List<Report> getMyReport() throws ClassNotFoundException, IllegalAccessException {
        User user = userUtil.getUser();
        QueryWrapper<Report> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        return reportMapper.selectList(wrapper);

    }

    @Override
    public int updateReportInfo(Report report) {
        return reportMapper.updateById(report);
    }

    @Override
    public int deleteReport(Long reportId) {
        return reportMapper.deleteById(reportId);
    }

    @Override
    public List<Report> getReportByStatus() {
        return reportMapper.selectList(null);
    }

    @Override
    public int auditReport(ReportAuditVo reportAuditVo) {

        return reportMapper.auditReport(reportAuditVo.getReportId(),reportAuditVo.getStatus());
    }
}
