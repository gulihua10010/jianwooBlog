package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.entity.example.ArticleTagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagsMapper {
    long countByExample(ArticleTagsExample example);

    int deleteByExample(ArticleTagsExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(ArticleTags record);

    int insertSelective(ArticleTags record);

    List<ArticleTags> selectByExample(ArticleTagsExample example);

    ArticleTags selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") ArticleTags record, @Param("example") ArticleTagsExample example);

    int updateByExample(@Param("record") ArticleTags record, @Param("example") ArticleTagsExample example);

    int updateByPrimaryKeySelective(ArticleTags record);

    int updateByPrimaryKey(ArticleTags record);
}