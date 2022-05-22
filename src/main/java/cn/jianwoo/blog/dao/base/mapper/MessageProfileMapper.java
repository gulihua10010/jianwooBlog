package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import cn.jianwoo.blog.entity.example.MessageProfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageProfileMapper {
    long countByExample(MessageProfileExample example);

    int deleteByExample(MessageProfileExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(MessageProfileWithBLOBs record);

    int insertSelective(MessageProfileWithBLOBs record);

    List<MessageProfileWithBLOBs> selectByExampleWithBLOBs(MessageProfileExample example);

    List<MessageProfile> selectByExample(MessageProfileExample example);

    MessageProfileWithBLOBs selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") MessageProfileWithBLOBs record, @Param("example") MessageProfileExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageProfileWithBLOBs record, @Param("example") MessageProfileExample example);

    int updateByExample(@Param("record") MessageProfile record, @Param("example") MessageProfileExample example);

    int updateByPrimaryKeySelective(MessageProfileWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MessageProfileWithBLOBs record);

    int updateByPrimaryKey(MessageProfile record);
}