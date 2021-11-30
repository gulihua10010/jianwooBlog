package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.service.bo.BizLogBO;
import cn.jianwoo.blog.service.param.PageParam;
import com.github.pagehelper.PageInfo;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-31 16:07
 */
public interface LogBizService {
    /**
     * 分页查询登录日志列表
     *
     * @param param 分页参数
     * @return
     * @author gulihua
     */
    PageInfo<BizLogBO> queryLoginLogPageList(PageParam param);

    /**
     * 分页查询业务日志列表
     *
     * @param param 分页参数
     * @return
     * @author gulihua
     */
    PageInfo<BizLogBO> queryBizLogPageList(PageParam param);
}
