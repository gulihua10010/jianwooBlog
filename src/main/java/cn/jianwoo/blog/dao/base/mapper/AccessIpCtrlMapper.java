package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.AccessIpCtrl;
import cn.jianwoo.blog.entity.example.AccessIpCtrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessIpCtrlMapper {
    long countByExample(AccessIpCtrlExample example);

    int deleteByExample(AccessIpCtrlExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(AccessIpCtrl record);

    int insertSelective(AccessIpCtrl record);

    List<AccessIpCtrl> selectByExample(AccessIpCtrlExample example);

    AccessIpCtrl selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") AccessIpCtrl record, @Param("example") AccessIpCtrlExample example);

    int updateByExample(@Param("record") AccessIpCtrl record, @Param("example") AccessIpCtrlExample example);

    int updateByPrimaryKeySelective(AccessIpCtrl record);

    int updateByPrimaryKey(AccessIpCtrl record);
}