package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.entity.example.EmailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailMapper {
    long countByExample(EmailExample example);

    int deleteByExample(EmailExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(Email record);

    int insertSelective(Email record);

    List<Email> selectByExampleWithBLOBs(EmailExample example);

    List<Email> selectByExample(EmailExample example);

    Email selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByExampleWithBLOBs(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByExample(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByPrimaryKeySelective(Email record);

    int updateByPrimaryKeyWithBLOBs(Email record);

    int updateByPrimaryKey(Email record);
}