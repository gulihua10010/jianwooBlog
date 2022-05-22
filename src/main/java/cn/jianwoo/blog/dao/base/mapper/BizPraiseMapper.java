package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.BizPraise;
import cn.jianwoo.blog.entity.example.BizPraiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizPraiseMapper {
    long countByExample(BizPraiseExample example);

    int deleteByExample(BizPraiseExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(BizPraise record);

    int insertSelective(BizPraise record);

    List<BizPraise> selectByExample(BizPraiseExample example);

    BizPraise selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") BizPraise record, @Param("example") BizPraiseExample example);

    int updateByExample(@Param("record") BizPraise record, @Param("example") BizPraiseExample example);

    int updateByPrimaryKeySelective(BizPraise record);

    int updateByPrimaryKey(BizPraise record);
}