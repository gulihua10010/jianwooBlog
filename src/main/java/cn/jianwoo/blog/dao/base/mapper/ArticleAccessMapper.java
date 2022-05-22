package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.ArticleAccess;
import cn.jianwoo.blog.entity.example.ArticleAccessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleAccessMapper {
    long countByExample(ArticleAccessExample example);

    int deleteByExample(ArticleAccessExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(ArticleAccess record);

    int insertSelective(ArticleAccess record);

    List<ArticleAccess> selectByExample(ArticleAccessExample example);

    ArticleAccess selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") ArticleAccess record, @Param("example") ArticleAccessExample example);

    int updateByExample(@Param("record") ArticleAccess record, @Param("example") ArticleAccessExample example);

    int updateByPrimaryKeySelective(ArticleAccess record);

    int updateByPrimaryKey(ArticleAccess record);
}