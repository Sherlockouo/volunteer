package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.pojo.Comment;
import com.volunteer.service.CommentService;
import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import com.volunteer.util.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Api("评论接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @ApiOperation("添加评论")
    @PostMapping("/register")
    @HasRole(role = {Role.USER,Role.ADMIN,Role.ADMIN})
    public Result register(@RequestBody Comment comment) throws ClassNotFoundException, IllegalAccessException {
        int res = commentService.registerComment(comment);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("更新评论信息")
    @PutMapping("/update")
    @HasRole(role = {Role.USER,Role.ADMIN,Role.ADMIN})
    public Result update(@RequestBody Comment comment) throws ClassNotFoundException, IllegalAccessException {
        int res = commentService.updateCommentInfo(comment);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("删除评论")
    @DeleteMapping("/delete/{commentId}")
    @HasRole(role = {Role.USER,Role.ADMIN,Role.ADMIN})
    public Result delete(@PathVariable Long commentId) {
        int res = commentService.deleteComment(commentId);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("获取评论信息")
    @GetMapping("/get/{commentId}")
    public Result get(@PathVariable Long commentId) {
        Comment commentinfo = commentService.getCommentinfo(commentId);
        if (commentinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, commentinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取文章评论")
    @GetMapping("/article/{articleId}")
    public Result getArticleComment(@PathVariable Long articleId) {
        List<Comment> commentinfo = commentService.getArticleComment(articleId);
        if (commentinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, commentinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取所有评论")
    @GetMapping("/all")
    public Result getAllComment() {
        List<Comment> commentinfo = commentService.getAllComment();
        if (commentinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, commentinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }
}
