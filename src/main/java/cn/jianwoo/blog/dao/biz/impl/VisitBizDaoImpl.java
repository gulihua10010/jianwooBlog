package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.VisitBizDao;
import cn.jianwoo.blog.dao.biz.mapper.VisitBizMapper;
import cn.jianwoo.blog.entity.extension.VisitExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 18:18
 */
@Service
public class VisitBizDaoImpl implements VisitBizDao {
    @Autowired
    private VisitBizMapper visitBizMapper;

    @Override
    public List<VisitExt> queryRecentVisit(Integer limit) {
        return visitBizMapper.selectRecentVisit(limit);
    }


    @Override
    public List<VisitExt> queryRecentVisit() {
        return visitBizMapper.selectRecentVisit(null);
    }
}
