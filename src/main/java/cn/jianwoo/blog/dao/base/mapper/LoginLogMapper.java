package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.entity.example.LoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginLogMapper {
    long countByExample(LoginLogExample example);

    int deleteByExample(LoginLogExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    List<LoginLog> selectByExample(LoginLogExample example);

    LoginLog selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByExample(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}