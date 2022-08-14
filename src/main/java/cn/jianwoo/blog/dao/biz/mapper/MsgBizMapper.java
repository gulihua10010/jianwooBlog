package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-08-02 18:32
 */
public interface MsgBizMapper {

    /**
     * 页面定时获取最新消息(首页)
     *
     * @param limit     取出多少条记录
     * @param currentIp 当前访问者的ip
     * @return
     * @author gulihua
     */
    List<MessageProfileWithBLOBs> selectMsgTimerMainList(@Param("limit") Integer limit,
                                                         @Param("currentIp") String currentIp);
}
