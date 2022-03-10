package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.LoginFailedEvent;
import cn.jianwoo.blog.entity.example.LoginFailedEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginFailedEventMapper {
    long countByExample(LoginFailedEventExample example);

    int deleteByExample(LoginFailedEventExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(LoginFailedEvent record);

    int insertSelective(LoginFailedEvent record);

    List<LoginFailedEvent> selectByExample(LoginFailedEventExample example);

    LoginFailedEvent selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") LoginFailedEvent record, @Param("example") LoginFailedEventExample example);

    int updateByExample(@Param("record") LoginFailedEvent record, @Param("example") LoginFailedEventExample example);

    int updateByPrimaryKeySelective(LoginFailedEvent record);

    int updateByPrimaryKey(LoginFailedEvent record);
}