package com.volunteer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author wb
 * @since 2022-04-10
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("userid")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("昵称")
    @TableField("username")
    private String username;

    @ApiModelProperty("账号")
    @TableField("account")
    private String account;

    @ApiModelProperty("密码")
    @TableField("password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty("身份证")
    @TableField("identity")
    private String identity;

    @ApiModelProperty("性别")
    @TableField("sex")
    private Short sex;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private String birthday;

    @ApiModelProperty("服务区域")
    @TableField("serve_area")
    private String serveArea;

    @ApiModelProperty("服务类别")
    @TableField("serve_type")
    private String serveType;

    @ApiModelProperty("服务时间")
    @TableField("serve_time")
    private BigDecimal serveTime;

    @ApiModelProperty("创建时间")
    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty("角色")
    @TableField("role_id")
    private Long roleId;

    @TableField("school")
    private String school;


}
