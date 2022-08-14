package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.exception.JwBlogException;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-04 23:57
 */
public interface MessageBoardBaseService {

    /**
     * 通过主键oid去查询留言
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    MessageBoard queryMessageByOid(Long oid) throws JwBlogException;
}
