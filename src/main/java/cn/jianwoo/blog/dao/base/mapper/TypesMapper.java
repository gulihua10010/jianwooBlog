package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.Types;
import cn.jianwoo.blog.entity.example.TypesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypesMapper {
    long countByExample(TypesExample example);


    int deleteByExample(TypesExample example);


    int deleteByPrimaryKey(Long oid);


    int insert(Types record);


    int insertSelective(Types record);


    List<Types> selectByExample(TypesExample example);


    Types selectByPrimaryKey(Long oid);


    int updateByExampleSelective(@Param("record") Types record, @Param("example") TypesExample example);


    int updateByExample(@Param("record") Types record, @Param("example") TypesExample example);


    int updateByPrimaryKeySelective(Types record);


    int updateByPrimaryKey(Types record);
}