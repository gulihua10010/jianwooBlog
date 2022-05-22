package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.entity.example.UserProfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserProfileMapper {
    long countByExample(UserProfileExample example);

    int deleteByExample(UserProfileExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(UserProfile record);

    int insertSelective(UserProfile record);

    List<UserProfile> selectByExample(UserProfileExample example);

    UserProfile selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") UserProfile record, @Param("example") UserProfileExample example);

    int updateByExample(@Param("record") UserProfile record, @Param("example") UserProfileExample example);

    int updateByPrimaryKeySelective(UserProfile record);

    int updateByPrimaryKey(UserProfile record);
}