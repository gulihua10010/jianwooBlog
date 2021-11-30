package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.BizEventLog;
import cn.jianwoo.blog.entity.example.BizEventLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizEventLogMapper {
    long countByExample(BizEventLogExample example);

    int deleteByExample(BizEventLogExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(BizEventLog record);

    int insertSelective(BizEventLog record);

    List<BizEventLog> selectByExample(BizEventLogExample example);

    BizEventLog selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") BizEventLog record, @Param("example") BizEventLogExample example);

    int updateByExample(@Param("record") BizEventLog record, @Param("example") BizEventLogExample example);

    int updateByPrimaryKeySelective(BizEventLog record);

    int updateByPrimaryKey(BizEventLog record);
}