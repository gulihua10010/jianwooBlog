package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.Module;
import cn.jianwoo.blog.entity.example.ModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleMapper {
    long countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExample(ModuleExample example);

    Module selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}