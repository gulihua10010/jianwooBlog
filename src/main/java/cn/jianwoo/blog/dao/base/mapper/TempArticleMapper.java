package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.entity.example.TempArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TempArticleMapper {
    long countByExample(TempArticleExample example);

    int deleteByExample(TempArticleExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(TempArticle record);

    int insertSelective(TempArticle record);

    List<TempArticle> selectByExampleWithBLOBs(TempArticleExample example);

    List<TempArticle> selectByExample(TempArticleExample example);

    TempArticle selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") TempArticle record, @Param("example") TempArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") TempArticle record, @Param("example") TempArticleExample example);

    int updateByExample(@Param("record") TempArticle record, @Param("example") TempArticleExample example);

    int updateByPrimaryKeySelective(TempArticle record);

    int updateByPrimaryKeyWithBLOBs(TempArticle record);

    int updateByPrimaryKey(TempArticle record);
}