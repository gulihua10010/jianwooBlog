package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.ArticleAccessTransDao;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.biz.ArticleAccessBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleAccess;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.entity.extension.AccessExt;
import cn.jianwoo.blog.entity.query.AccessQuery;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.AsyncIpEnum;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.exception.ArticleAccessBizException;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.ArticleBaseService;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.biz.ArticleAccessBizService;
import cn.jianwoo.blog.service.bo.AccessBO;
import cn.jianwoo.blog.service.param.AccessParam;
import cn.jianwoo.blog.task.bo.TaskDataD0020BO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.TransactionUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ArticleAccessBizServiceImpl implements ArticleAccessBizService {
    @Autowired
    ArticleAccessBizDao articleAccessBizDao;
    @Autowired
    ArticleAccessTransDao articleAccessTransDao;
    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;

    @Autowired
    private ArticleBaseService articleBaseService;
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private TransactionUtils transactionUtils;

    @Override
    public List<AccessBO> queryRecentAccess(Integer limit) {
        List<AccessExt> accessExtList = articleAccessBizDao.queryRecentAccess(limit);
        List<AccessBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(accessExtList)) {
            accessExtList.forEach(o -> {
                AccessBO accessBO = new AccessBO();
                BeanUtils.copyProperties(o, accessBO);
                list.add(accessBO);
            });
        }
        return list;
    }


    @Override
    public PageInfo<AccessBO> queryRecentAccessPageList(AccessParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        AccessQuery query = new AccessQuery();
        BeanUtils.copyProperties(param, query);
        List<AccessExt> accessExtList = articleAccessBizDao.queryRecentAccess();
        List<AccessBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(accessExtList)) {
            accessExtList.forEach(o -> {
                AccessBO accessBO = new AccessBO();
                BeanUtils.copyProperties(o, accessBO);
                accessBO.setArticleTitle(o.getArticleTitle());
                list.add(accessBO);
            });
        }
        PageInfo<AccessBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(pageInfo.getPages());
        //总条数
        pageInfo.setTotal(pageInfo.getTotal());
        return pageInfo;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAccessRecord(String ip, Long artOid) throws JwBlogException {
        Date now = DateUtil.getNow();
        Article article = articleBaseService.queryArticleByOid(artOid);
        if (!ArticleStatusEnum.PUBLISHED.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_NOT_PUBLISHED_CN.format(article.getTitle()).print();
        }
        ArticleAccess existRecord = articleAccessTransDao.queryArticleAccessByArtOidAndIp(artOid, ip);
        if (existRecord != null) {
            return;

        }

        ArticleAccess accessRecord = JwBuilder.of(ArticleAccess::new)
                .with(ArticleAccess::setArticleOid, artOid)
                .with(ArticleAccess::setArticleTitle, article.getTitle())
                .with(ArticleAccess::setAccessIp, ip)
                .with(ArticleAccess::setAccessTime, now)
                .with(ArticleAccess::setCreateTime, now)
                .with(ArticleAccess::setUpdateTime, now)
                .build();
        try {
            articleAccessTransDao.doInsertSelective(accessRecord);
        } catch (DaoException e) {
            log.error("ArticleAccessBizServiceImpl.createAccessRecord exec failed, e:\n", e);
            throw ArticleAccessBizException.CREATE_FAILED_EXCEPTION.format(artOid, ip).print();

        }
        try {
            ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
            newArticle.setOid(article.getOid());
            newArticle.setReadCount(article.getReadCount() + 1);
            newArticle.setUpdateTime(now);
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("ArticleViairBizServiceImpl.createAccessRecord exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(artOid, ip).print();

        }

        //执行异步任务
        TaskDataD0020BO taskDataD0020BO = new TaskDataD0020BO();
        taskDataD0020BO.setOid(accessRecord.getOid());
        taskDataD0020BO.setIp(accessRecord.getAccessIp());
        taskDataD0020BO.setAsyncIpType(AsyncIpEnum.ACCESS.name());

        Long taskId = null;
        try {

            taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0020.getValue(), JSONObject.toJSONString(taskDataD0020BO));
        } catch (JwBlogException e) {
            log.error("\r\n>>ArticleViairBizServiceImpl.createAccessRecord exec failed, e\r\n", e);
        }
        if (taskId != null) {
            transactionUtils.doTriggerTaskAfterCommit(taskId);
        }
    }
}