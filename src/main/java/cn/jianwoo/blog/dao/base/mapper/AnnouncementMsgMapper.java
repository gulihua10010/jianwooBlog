package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.AnnouncementMsg;
import cn.jianwoo.blog.entity.example.AnnouncementMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnouncementMsgMapper {
    long countByExample(AnnouncementMsgExample example);

    int deleteByExample(AnnouncementMsgExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(AnnouncementMsg record);

    int insertSelective(AnnouncementMsg record);

    List<AnnouncementMsg> selectByExampleWithBLOBs(AnnouncementMsgExample example);

    List<AnnouncementMsg> selectByExample(AnnouncementMsgExample example);

    AnnouncementMsg selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") AnnouncementMsg record, @Param("example") AnnouncementMsgExample example);

    int updateByExampleWithBLOBs(@Param("record") AnnouncementMsg record, @Param("example") AnnouncementMsgExample example);

    int updateByExample(@Param("record") AnnouncementMsg record, @Param("example") AnnouncementMsgExample example);

    int updateByPrimaryKeySelective(AnnouncementMsg record);

    int updateByPrimaryKeyWithBLOBs(AnnouncementMsg record);

    int updateByPrimaryKey(AnnouncementMsg record);
}