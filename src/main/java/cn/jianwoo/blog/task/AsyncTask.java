package cn.jianwoo.blog.task;

import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.dao.base.VisitTransDao;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.Visit;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.service.biz.NetWorkService;

import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * @author GuLihua
 * @Description
 * @date 2020-12-16 18:10
 */
@Component
@Async("jianwooTaskExecutor")
public class AsyncTask {
    private static final Logger log = LoggerFactory.getLogger(AsyncTask.class);
    private static final String IP_REX_PATTERN = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
    @Autowired
    private NetWorkService netWorkService;
    @Autowired
    private CommentTransDao commentTransDao;
    @Autowired
    private VisitTransDao visitTransDao;


    public Future<String> execCommentIpAreaTask(Long oid) {
        log.info(">> Start async task execCommentIpAreaTask");
        try {
            Comment comment = commentTransDao.queryCommentByPrimaryKey(oid);
            if (StringUtils.isNotBlank(comment.getIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(comment.getIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(comment.getIp().trim());
                    Comment newComment = new Comment();
                    newComment.setOid(oid);
                    newComment.setArea(area);
                    commentTransDao.doUpdateByPrimaryKeySelective(newComment);

                }
            }

        } catch (DaoException e) {
            log.error("jianwooTaskExecutor.execCommentIpAreaTask exec failed, e:", e);
        }

        log.info(">> End async task execCommentIpAreaTask");

        return new AsyncResult<>("execCommentIpAreaTask");
    }

    public Future<String> execVisitIpAreaTask(Long oid) {

        log.info(">> Start async task execVisitIpAreaTask");
        try {
            Visit visit = visitTransDao.queryVisitByPrimaryKey(oid);
            if (StringUtils.isNotBlank(visit.getIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(visit.getIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(visit.getIp().trim());
                    Visit newVisit = new Visit();
                    newVisit.setOid(oid);
                    newVisit.setArea(area);
                    visitTransDao.doUpdateByPrimaryKeySelective(newVisit);

                }
            }

        } catch (DaoException e) {
            log.error("jianwooTaskExecutor.execCommentIpAreaTask exec failed, e:", e);
        }
        log.info(">> End async task execVisitIpAreaTask");

        return new AsyncResult<>("execVisitIpAreaTask");
    }
}
