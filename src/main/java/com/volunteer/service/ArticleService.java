package com.volunteer.service;

import com.volunteer.pojo.Article;
import com.volunteer.pojo.Article;
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
public interface ArticleService extends IService<Article> {

    int registerArticle(Article article);

    Article getArticleinfo(Long articleId);

    int updateArticleInfo(Article article);

    int deleteArticle(Long articleId);

    List<Article> getArticleList(String publishby);
}
