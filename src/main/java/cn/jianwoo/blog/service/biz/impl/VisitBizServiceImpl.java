package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.dao.biz.VisitBizDao;
import cn.jianwoo.blog.entity.extension.VisitExt;
import cn.jianwoo.blog.entity.query.VisitParam;
import cn.jianwoo.blog.service.biz.VisitBizService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitBizServiceImpl implements VisitBizService {
    @Autowired
    VisitBizDao visitBizDao;

    @Override
    public List<VisitExt> queryRecentVisit(Integer limit) {
        return visitBizDao.queryRecentVisit(limit);
    }


    @Override
    public PageInfo<VisitExt> queryRecentVisitPageList(VisitParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<VisitExt> list = visitBizDao.queryRecentVisit();
        PageInfo<VisitExt> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }
}