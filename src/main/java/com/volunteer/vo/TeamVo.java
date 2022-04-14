package com.volunteer.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TeamVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("队伍编号")
    private Long teamId;

    @ApiModelProperty("队伍联系人")
    private Long userId;

    @ApiModelProperty("队伍名称")
    private String teamName;

    @ApiModelProperty("队伍的创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date communityCreatedate;

    @ApiModelProperty("队伍所在的区域")
    private List<String> serveArea;

    @ApiModelProperty("队伍服务类型")
    private List<String> serveType;

    @ApiModelProperty("队伍状态 0: 审核中 1： 通过 2： 不通过")
    private Integer status;

    @ApiModelProperty("团队简介")
    private String introduce;

    @ApiModelProperty("注册人账号")
    private String account;

    @ApiModelProperty("团队logo")
    private String logo;

    @ApiModelProperty("负责人信息")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserVo userVo;
}
