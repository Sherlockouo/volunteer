package com.volunteer.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("userid")
    private Long id;

    @ApiModelProperty("昵称")
    private String username;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty("身份证")
    private String identity;

    @ApiModelProperty("性别")
    private Short sex;

    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    @ApiModelProperty("服务区域")
    private List<String> serveArea;

    @ApiModelProperty("服务类别")
    private List<String> serveType;

    @ApiModelProperty("服务时间")
    private BigDecimal serveTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @ApiModelProperty("角色")
    private Long roleId;

    @ApiModelProperty("角色")
    private List<TeamVo> teamList;

    @ApiModelProperty("所在学校")
    private String school;
}
