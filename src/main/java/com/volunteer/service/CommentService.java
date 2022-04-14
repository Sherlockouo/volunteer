package com.volunteer.service;

import com.volunteer.pojo.Comment;
import com.volunteer.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
public interface CommentService extends IService<Comment> {

    int registerComment(Comment comment) throws ClassNotFoundException, IllegalAccessException;

    Comment getCommentinfo(Long commentId);

    int updateCommentInfo(Comment comment);

    int deleteComment(Long commentId);

    List<Comment> getArticleComment(Long articleId);

    List<Comment> getAllComment();
}
