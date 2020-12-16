package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.example.ArticleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    long countByExample(ArticleExample example);


    int deleteByExample(ArticleExample example);


    int deleteByPrimaryKey(Long oid);


    int insert(Article record);


    int insertSelective(Article record);


    List<Article> selectByExample(ArticleExample example);


    Article selectByPrimaryKey(Long oid);


    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);


    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);


    int updateByPrimaryKeySelective(Article record);


    int updateByPrimaryKey(Article record);
}