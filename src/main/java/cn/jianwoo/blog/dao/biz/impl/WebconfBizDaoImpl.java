package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.WebconfBizDao;
import cn.jianwoo.blog.dao.biz.mapper.WebconfBizMapper;
import cn.jianwoo.blog.entity.Webconf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-20 10:25
 */
@Service
public class WebconfBizDaoImpl implements WebconfBizDao {
    @Autowired
    WebconfBizMapper webconfBizMapper;

    @Override
    public List<Webconf> queryAllWebconf() {
        return webconfBizMapper.selectAllWebconf();
    }
}
