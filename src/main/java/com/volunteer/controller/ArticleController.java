package com.volunteer.controller;

import com.volunteer.aspect.HasRole;
import com.volunteer.pojo.Article;
import com.volunteer.service.ArticleService;
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
@Api("文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    ArticleService articleService;

    @ApiOperation("注册文章")
    @PostMapping("/register")
    @HasRole(role = {Role.USER,Role.ADMIN})
    public Result register(@RequestBody Article article) {
        int res = articleService.registerArticle(article);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("更新文章信息")
    @PutMapping("/update")
    @HasRole(role = {Role.USER})
    public Result update(@RequestBody Article article) throws ClassNotFoundException, IllegalAccessException {
        int res = articleService.updateArticleInfo(article);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("删除文章")
    @DeleteMapping("/delete/{articleId}")
    @HasRole(role = {Role.ADMIN, Role.USER})
    public Result delete(@PathVariable Long articleId) {
        int res = articleService.deleteArticle(articleId);
        if (res > 0) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, null);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }

    }

    @ApiOperation("获取文章信息")
    @GetMapping("/get/{articleId}")
    public Result getUserinfo(@PathVariable Long articleId) {
        Article articleinfo = articleService.getArticleinfo(articleId);
        if (articleinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, articleinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }

    @ApiOperation("获取文章列表 publishby 1： 用户 2： 团队 3： 管理员")
    @GetMapping("/list")
    public Result getList(String publishby) {
        List<Article> articleinfo = articleService.getArticleList(publishby);
        if (articleinfo != null) {
            return new Result(ResultEnum.SUCCESS_MESSAGE, articleinfo);
        } else {
            return new Result(ResultEnum.PARAM_ERROR, null);
        }
    }
    
}
