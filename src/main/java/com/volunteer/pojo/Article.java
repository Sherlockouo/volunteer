package com.volunteer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
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
@TableName("article")
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("创建时间")
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @ApiModelProperty("来源")
    @TableField("source")
    private String source;

    @ApiModelProperty("文章内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("发布者 1：用户 2：团队管理员 3：管理员")
    @TableField("publishby")
    private Integer publishby;


}
