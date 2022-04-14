package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.pojo.Report;
import com.volunteer.service.ReportService;
import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import com.volunteer.util.Role;
import com.volunteer.vo.ReportAuditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  举报
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Api("举报接口")
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @ApiOperation("注册举报")
    @PostMapping("/register")
    public Result register(@RequestBody Report report) {
        int res = reportService.registerReport(report);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("更新举报信息")
    @PutMapping("/update")
    @HasRole(role = {Role.USER,Role.ADMIN,Role.TEAM})
    public Result update(@RequestBody Report report){
        int res = reportService.updateReportInfo(report);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("删除举报")
    @DeleteMapping("/delete/{reportId}")
    @HasRole(role = {Role.ADMIN})
    public Result delete(@PathVariable Long reportId) {
        int res = reportService.deleteReport(reportId);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取用户自己举报过信息")
    @GetMapping("/my")
    public Result getMy() throws ClassNotFoundException, IllegalAccessException {
        List<Report> reportinfo = reportService.getMyReport();
        if (reportinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, reportinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取举报信息")
    @GetMapping("/get/{reportId}")
    public Result get(@PathVariable Long reportId) {
        Report reportinfo = reportService.getReportinfo(reportId);
        if (reportinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, reportinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取所有举报信息")
    @GetMapping("/all")
    @HasRole(role = {Role.ADMIN})
    public Result get() {
        List<Report> reportinfo = reportService.getReportByStatus();
        if (reportinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, reportinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("审核举报信息")
    @PutMapping("/audit")
    @HasRole(role = {Role.ADMIN})
    public Result audit(@RequestBody ReportAuditVo reportAuditVo){
        reportService.auditReport(reportAuditVo);
        return new Result(ResultEnum.SUCCESS_MESSAGE,null);
    }

}
