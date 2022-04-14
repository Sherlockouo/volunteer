package com.volunteer.mapper;

import com.volunteer.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from comment where post_id = #{articleId} and reply_id = 0")
    List<Comment> getArticleComments(Long articleId);

//    @Select("with recursive cmt as(\n" +
//            "SELECT c.id,post_id,user_id,create_date,`comment`,reply_id from comment c where c.post_id = #{articleId} and c.reply_id = #{replyId}\n" +
//            "union all \n" +
//            "select t.id,t.post_id,t.user_id,t.create_date,t.`comment`,t.reply_id from `comment` t  INNER JOIN cmt p on p.id = t.reply_id\n" +
//            ")\n" +
//            "select id,post_id,user_id,create_date,`comment`,reply_id from cmt;")
    @Select("select * from comment where post_id = #{articleId} and reply_id = #{replyId}")
    List<Comment> getArticleCommentsChild(Long articleId,Long replyId);
}
