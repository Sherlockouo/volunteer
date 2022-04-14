package com.volunteer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Getter
@Setter
@ToString
@TableName("activity")
@ApiModel(value = "Activity对象", description = "")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("活动编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("社团编号")
    @TableField("team_id")
    private Long teamId;

    @ApiModelProperty("活动名称")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("活动简介")
    @TableField("`desc`")
    private String desc;

    @ApiModelProperty("logo")
    @TableField("logo")
    private String logo;

    @ApiModelProperty("活动地点")
    @TableField("address")
    private String address;

    @ApiModelProperty("活动开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("start_datetime")
    private Date startDatetime;

    @ApiModelProperty("活动结束日期")
    @TableField("end_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDatetime;

    @ApiModelProperty("活动时长")
    @TableField("length")
    private BigDecimal length;

    @ApiModelProperty("状态 审批中:0 通过:1 不通过:2")
    @TableField("`status`")
    private Integer status;

    @ApiModelProperty("活动类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("修改意见")
    @TableField("modify_desc")
    private String modifyDesc;

    @ApiModelProperty("创建日期")
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @ApiModelProperty("招募人数")
    @TableField("recruit_number")
    private Integer recruitNumber;

}
