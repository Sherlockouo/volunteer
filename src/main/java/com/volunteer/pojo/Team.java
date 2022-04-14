package com.volunteer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
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
@TableName("team")
@ApiModel(value = "Team对象", description = "")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("队伍编号")
    @TableId(value = "team_id", type = IdType.AUTO)
    private Long teamId;

    @ApiModelProperty("队伍联系人")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("队伍名称")
    @TableField("team_name")
    private String teamName;

    @ApiModelProperty("队伍的创建时间")
    @TableField("community_createdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date communityCreatedate;

    @ApiModelProperty("队伍所在的区域")
    @TableField("serve_area")
    private String serveArea;

    @ApiModelProperty("团队简介")
    @TableField("introduce")
    private String introduce;

    @TableField("logo")
    @ApiModelProperty("团队logo")
    private String logo;

    @ApiModelProperty("队伍状态 0: 审核中 1： 通过 2： 不通过")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("队伍服务类型")
    @TableField("serve_type")
    private String serveType;


}
