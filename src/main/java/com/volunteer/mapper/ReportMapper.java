package com.volunteer.mapper;

import com.volunteer.pojo.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Mapper
public interface ReportMapper extends BaseMapper<Report> {

    @Update("update report set report_status = #{status} where id = #{reportId}")
    int auditReport(Long reportId, Integer status);

    @Select("select * from report where ")
    List<Report> getReportByStatus(Integer status);
}
