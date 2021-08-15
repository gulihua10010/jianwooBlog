package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.example.EmailTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailTemplateMapper {
    long countByExample(EmailTemplateExample example);

    int deleteByExample(EmailTemplateExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(EmailTemplate record);

    int insertSelective(EmailTemplate record);

    List<EmailTemplate> selectByExampleWithBLOBs(EmailTemplateExample example);

    List<EmailTemplate> selectByExample(EmailTemplateExample example);

    EmailTemplate selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") EmailTemplate record, @Param("example") EmailTemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") EmailTemplate record, @Param("example") EmailTemplateExample example);

    int updateByExample(@Param("record") EmailTemplate record, @Param("example") EmailTemplateExample example);

    int updateByPrimaryKeySelective(EmailTemplate record);

    int updateByPrimaryKeyWithBLOBs(EmailTemplate record);

    int updateByPrimaryKey(EmailTemplate record);
}