package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.MessageTemplate;
import cn.jianwoo.blog.entity.example.MessageTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageTemplateMapper {
    long countByExample(MessageTemplateExample example);

    int deleteByExample(MessageTemplateExample example);

    int deleteByPrimaryKey(String busiSceneCode);

    int insert(MessageTemplate record);

    int insertSelective(MessageTemplate record);

    List<MessageTemplate> selectByExampleWithBLOBs(MessageTemplateExample example);

    List<MessageTemplate> selectByExample(MessageTemplateExample example);

    MessageTemplate selectByPrimaryKey(String busiSceneCode);

    int updateByExampleSelective(@Param("record") MessageTemplate record, @Param("example") MessageTemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageTemplate record, @Param("example") MessageTemplateExample example);

    int updateByExample(@Param("record") MessageTemplate record, @Param("example") MessageTemplateExample example);

    int updateByPrimaryKeySelective(MessageTemplate record);

    int updateByPrimaryKeyWithBLOBs(MessageTemplate record);

    int updateByPrimaryKey(MessageTemplate record);
}