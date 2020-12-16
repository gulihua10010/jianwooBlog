package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.Visit;
import cn.jianwoo.blog.entity.example.VisitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisitMapper {
    long countByExample(VisitExample example);


    int deleteByExample(VisitExample example);


    int deleteByPrimaryKey(Long oid);


    int insert(Visit record);


    int insertSelective(Visit record);


    List<Visit> selectByExample(VisitExample example);


    Visit selectByPrimaryKey(Long oid);


    int updateByExampleSelective(@Param("record") Visit record, @Param("example") VisitExample example);


    int updateByExample(@Param("record") Visit record, @Param("example") VisitExample example);


    int updateByPrimaryKeySelective(Visit record);


    int updateByPrimaryKey(Visit record);
}