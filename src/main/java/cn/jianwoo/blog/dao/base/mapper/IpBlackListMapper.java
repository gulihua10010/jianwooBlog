package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.IpBlackList;
import cn.jianwoo.blog.entity.example.IpBlackListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IpBlackListMapper {
    long countByExample(IpBlackListExample example);

    int deleteByExample(IpBlackListExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(IpBlackList record);

    int insertSelective(IpBlackList record);

    List<IpBlackList> selectByExample(IpBlackListExample example);

    IpBlackList selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") IpBlackList record, @Param("example") IpBlackListExample example);

    int updateByExample(@Param("record") IpBlackList record, @Param("example") IpBlackListExample example);

    int updateByPrimaryKeySelective(IpBlackList record);

    int updateByPrimaryKey(IpBlackList record);
}