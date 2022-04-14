package com.volunteer.service.impl;

import com.volunteer.mapper.UserMapper;
import com.volunteer.pojo.Comment;
import com.volunteer.mapper.CommentMapper;
import com.volunteer.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteer.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserUtil userUtil;

    @Override
    public int registerComment(Comment comment) throws ClassNotFoundException, IllegalAccessException {
        comment.setUserId(userUtil.getUser().getId());
        return commentMapper.insert(comment);
    }

    @Override
    public Comment getCommentinfo(Long commentId) {
        return commentMapper.selectById(commentId);
    }

    @Override
    public int updateCommentInfo(Comment comment) {
        return commentMapper.updateById(comment);
    }

    @Override
    public int deleteComment(Long commentId) {
        return commentMapper.deleteById(commentId);
    }

    @Override
    public List<Comment> getArticleComment(Long articleId) {
        return commentMapper.getArticleComments(articleId).stream().filter(c -> {

            c.setUsername(userMapper.selectById(c.getUserId()).getUsername());

            try {
                c.setCommentList(getReply(articleId, c.getId()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return true;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Comment> getAllComment() {
        return commentMapper.selectList(null);
    }

    public List<Comment> getReply(Long articleId, Long replyId) throws ClassNotFoundException, IllegalAccessException {
        List<Comment> replyList = commentMapper.getArticleCommentsChild(articleId, replyId);
        if (replyList.size() > 0) {
            for (Comment c : replyList) {
                c.setUsername(userMapper.selectById(c.getUserId()).getUsername());
                c.setCommentList(getReply(articleId, c.getId()));
            }
        }
        return replyList;
    }

}
