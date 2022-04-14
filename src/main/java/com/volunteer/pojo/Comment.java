package com.volunteer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@TableName("comment")
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("post的id")
    @TableField("post_id")
    private Long postId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("用户名")
    @TableField(exist = false)
    private String username;

    @ApiModelProperty("创建时间")
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @ApiModelProperty("评论内容")
    @TableField("comment")
    private String comment;

    @ApiModelProperty("评论回复")
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Comment> commentList;

    @ApiModelProperty("恢复的评论id 顶层为0")
    @TableField("reply_id")
    private Long replyId;


}
