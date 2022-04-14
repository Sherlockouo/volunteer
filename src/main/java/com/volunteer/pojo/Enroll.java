package com.volunteer.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

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
@TableName("enroll")
@ApiModel(value = "Enroll对象", description = "")
public class Enroll implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("0 审批中 1 通过 2 不通过")
    @TableField("enroll_status")
    private Integer enrollStatus;

    @ApiModelProperty("参与的活动")
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Activity activity;


}
