package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.WebconfFacade;
import cn.jianwoo.blog.entity.example.WebconfFacadeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebconfFacadeMapper {
    long countByExample(WebconfFacadeExample example);

    int deleteByExample(WebconfFacadeExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(WebconfFacade record);

    int insertSelective(WebconfFacade record);

    List<WebconfFacade> selectByExample(WebconfFacadeExample example);

    WebconfFacade selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") WebconfFacade record, @Param("example") WebconfFacadeExample example);

    int updateByExample(@Param("record") WebconfFacade record, @Param("example") WebconfFacadeExample example);

    int updateByPrimaryKeySelective(WebconfFacade record);

    int updateByPrimaryKey(WebconfFacade record);
}