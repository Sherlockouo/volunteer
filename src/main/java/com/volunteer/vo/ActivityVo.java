package com.volunteer.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel("活动")
public class ActivityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("活动编号")
    private Long id;

    @ApiModelProperty("社团编号")
    private Long teamId;

    @ApiModelProperty("活动名称")
    private String name;

    @ApiModelProperty("活动简介")
    private String desc;

    @ApiModelProperty("活动地点")
    private String address;

    @ApiModelProperty("活动开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDatetime;

    @ApiModelProperty("活动结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDatetime;

    @ApiModelProperty("活动时长")
    private BigDecimal length;

    @ApiModelProperty("状态 审批中:0 通过:1 不通过:2")
    private Integer status;

    @ApiModelProperty("活动类型")
    private List<String> type;

    @ApiModelProperty("修改意见")
    private String modifyDesc;

    @ApiModelProperty("logo")
    private String logo;

    @ApiModelProperty("创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @ApiModelProperty("招募状态 -1 未开始 0 招募中 1招募结束")
    private Integer recruitStatus;

    @ApiModelProperty("剩余招募人数")
    private Integer recruitNumber;

}


