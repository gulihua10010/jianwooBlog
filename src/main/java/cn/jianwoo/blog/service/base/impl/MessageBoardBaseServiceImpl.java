package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.dao.base.MessageBoardTransDao;
import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.MessageBoardBizException;
import cn.jianwoo.blog.service.base.MessageBoardBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gulihua
 * @Description
 * @date 2022-08-06 23:16
 */
@Service
@Slf4j
public class MessageBoardBaseServiceImpl implements MessageBoardBaseService {
    @Autowired
    private MessageBoardTransDao messageBoardTransDao;

    @Override
    public MessageBoard queryMessageByOid(Long oid) throws JwBlogException {
        try {
            return messageBoardTransDao.queryMessageBoardByPrimaryKey(oid);
        } catch (DaoException e) {
            throw MessageBoardBizException.THIS_MESSAGE_NOT_EXIST_EXCEPTION_CN.print();

        }
    }
}
