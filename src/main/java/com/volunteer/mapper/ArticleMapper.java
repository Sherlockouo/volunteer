package com.volunteer.mapper;

import com.volunteer.pojo.Article;
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
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select id,title,create_date,source,content,publishby from article where publishby = #{publishby}")
    List<Article> getSourceArticle(String publishby);
}
