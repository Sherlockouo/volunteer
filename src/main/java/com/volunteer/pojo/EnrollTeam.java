package com.volunteer.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2022-04-12
 */
@Getter
@Setter
@TableName("enroll_team")
@ApiModel(value = "EnrollTeam对象", description = "")
public class EnrollTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ApiModelProperty("userid")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("团队id")
    @TableField("team_id")
    private Long teamId;

    @ApiModelProperty("0 审批中 1 通过 2 不通过")
    @TableField("enroll_status")
    private Integer enrollStatus;

    @TableField("create_date")
    private LocalDateTime createDate;


}
