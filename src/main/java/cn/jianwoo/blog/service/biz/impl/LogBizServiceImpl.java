package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.dao.base.BizEventLogTransDao;
import cn.jianwoo.blog.dao.base.LoginLogTransDao;
import cn.jianwoo.blog.entity.BizEventLog;
import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.service.biz.LogBizService;
import cn.jianwoo.blog.service.bo.BizLogBO;
import cn.jianwoo.blog.service.param.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-31 16:43
 */
@Service
@Slf4j
public class LogBizServiceImpl implements LogBizService {
    @Autowired
    private LoginLogTransDao loginLogTransDao;
    @Autowired
    private BizEventLogTransDao bizEventLogTransDao;

    @Override
    public PageInfo<BizLogBO> queryLoginLogPageList(PageParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<LoginLog> loginLogList = loginLogTransDao.queryAllList();

        List<BizLogBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(loginLogList)) {
            loginLogList.forEach(o -> {
                BizLogBO bo = new BizLogBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<BizLogBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public PageInfo<BizLogBO> queryBizLogPageList(PageParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<BizEventLog> bizEventLogList = bizEventLogTransDao.queryAllList();

        List<BizLogBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bizEventLogList)) {
            bizEventLogList.forEach(o -> {
                BizLogBO bo = new BizLogBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<BizLogBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
