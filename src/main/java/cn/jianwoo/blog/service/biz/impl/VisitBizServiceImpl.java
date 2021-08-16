package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.dao.biz.VisitBizDao;
import cn.jianwoo.blog.entity.extension.VisitExt;
import cn.jianwoo.blog.entity.query.VisitQuery;
import cn.jianwoo.blog.service.biz.VisitBizService;
import cn.jianwoo.blog.service.bo.VisitBO;
import cn.jianwoo.blog.service.param.VisitParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitBizServiceImpl implements VisitBizService {
    @Autowired
    VisitBizDao visitBizDao;

    @Override
    public List<VisitBO> queryRecentVisit(Integer limit) {
        List<VisitExt> visitExtList = visitBizDao.queryRecentVisit(limit);
        List<VisitBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(visitExtList)) {
            visitExtList.forEach(o -> {
                VisitBO visitBO = new VisitBO();
                BeanUtils.copyProperties(o, visitBO);
                list.add(visitBO);
            });
        }
        return list;
    }


    @Override
    public PageInfo<VisitBO> queryRecentVisitPageList(VisitParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        VisitQuery query = new VisitQuery();
        BeanUtils.copyProperties(param, query);
        List<VisitExt> visitExtList = visitBizDao.queryRecentVisit();
        List<VisitBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(visitExtList)) {
            visitExtList.forEach(o -> {
                VisitBO visitBO = new VisitBO();
                BeanUtils.copyProperties(o, visitBO);
                list.add(visitBO);
            });
        }
        PageInfo<VisitBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(pageInfo.getPages());
        //总条数
        pageInfo.setTotal(pageInfo.getTotal());
        return pageInfo;

    }
}