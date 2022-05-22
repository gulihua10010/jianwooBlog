package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.AsyncProcTaskTypeCfg;
import cn.jianwoo.blog.entity.example.AsyncProcTaskTypeCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AsyncProcTaskTypeCfgMapper {
    long countByExample(AsyncProcTaskTypeCfgExample example);

    int deleteByExample(AsyncProcTaskTypeCfgExample example);

    int deleteByPrimaryKey(String taskType);

    int insert(AsyncProcTaskTypeCfg record);

    int insertSelective(AsyncProcTaskTypeCfg record);

    List<AsyncProcTaskTypeCfg> selectByExample(AsyncProcTaskTypeCfgExample example);

    AsyncProcTaskTypeCfg selectByPrimaryKey(String taskType);

    int updateByExampleSelective(@Param("record") AsyncProcTaskTypeCfg record, @Param("example") AsyncProcTaskTypeCfgExample example);

    int updateByExample(@Param("record") AsyncProcTaskTypeCfg record, @Param("example") AsyncProcTaskTypeCfgExample example);

    int updateByPrimaryKeySelective(AsyncProcTaskTypeCfg record);

    int updateByPrimaryKey(AsyncProcTaskTypeCfg record);
}