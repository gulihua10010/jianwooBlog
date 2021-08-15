package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.example.TagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagsMapper {
    long countByExample(TagsExample example);

    int deleteByExample(TagsExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(Tags record);

    int insertSelective(Tags record);

    List<Tags> selectByExample(TagsExample example);

    Tags selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") Tags record, @Param("example") TagsExample example);

    int updateByExample(@Param("record") Tags record, @Param("example") TagsExample example);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);
}