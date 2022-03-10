package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.entity.example.WebconfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebconfMapper {
    long countByExample(WebconfExample example);

    int deleteByExample(WebconfExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(Webconf record);

    int insertSelective(Webconf record);

    List<Webconf> selectByExample(WebconfExample example);

    Webconf selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") Webconf record, @Param("example") WebconfExample example);

    int updateByExample(@Param("record") Webconf record, @Param("example") WebconfExample example);

    int updateByPrimaryKeySelective(Webconf record);

    int updateByPrimaryKey(Webconf record);
}