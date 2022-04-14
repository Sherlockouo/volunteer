package com.volunteer.service;

import com.volunteer.pojo.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.volunteer.vo.ReportAuditVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
public interface ReportService extends IService<Report> {
    int registerReport(Report report);

    Report getReportinfo(Long reportId);

    List<Report> getUserReportinfo(Long reportId);

    List<Report> getMyReport() throws ClassNotFoundException, IllegalAccessException;

    int updateReportInfo(Report report);

    int deleteReport(Long reportId);

    List<Report> getReportByStatus();

    int auditReport(ReportAuditVo reportAuditVo);
}
