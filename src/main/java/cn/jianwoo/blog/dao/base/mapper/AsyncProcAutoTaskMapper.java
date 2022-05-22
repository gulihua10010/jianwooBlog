package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.AsyncProcAutoTask;
import cn.jianwoo.blog.entity.example.AsyncProcAutoTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AsyncProcAutoTaskMapper {
    long countByExample(AsyncProcAutoTaskExample example);

    int deleteByExample(AsyncProcAutoTaskExample example);

    int deleteByPrimaryKey(Long taskId);

    int insert(AsyncProcAutoTask record);

    int insertSelective(AsyncProcAutoTask record);

    List<AsyncProcAutoTask> selectByExample(AsyncProcAutoTaskExample example);

    AsyncProcAutoTask selectByPrimaryKey(Long taskId);

    int updateByExampleSelective(@Param("record") AsyncProcAutoTask record, @Param("example") AsyncProcAutoTaskExample example);

    int updateByExample(@Param("record") AsyncProcAutoTask record, @Param("example") AsyncProcAutoTaskExample example);

    int updateByPrimaryKeySelective(AsyncProcAutoTask record);

    int updateByPrimaryKey(AsyncProcAutoTask record);
}