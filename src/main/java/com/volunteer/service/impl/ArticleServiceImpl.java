package com.volunteer.service.impl;

import com.volunteer.pojo.Article;
import com.volunteer.mapper.ArticleMapper;
import com.volunteer.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wb
 * @since 2022-04-05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int registerArticle(Article article) {
        return articleMapper.insert(article);
    }

    @Override
    public Article getArticleinfo(Long articleId) {
        return articleMapper.selectById(articleId);
    }

    @Override
    public int updateArticleInfo(Article article) {
        return articleMapper.updateById(article);
    }

    @Override
    public int deleteArticle(Long articleId) {
        return articleMapper.deleteById(articleId);
    }

    @Override
    public List<Article> getArticleList(String publishby) {
        return articleMapper.getSourceArticle(publishby);
    }
}
