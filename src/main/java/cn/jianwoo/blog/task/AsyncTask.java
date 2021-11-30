package cn.jianwoo.blog.task;

import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.dao.base.BizEventLogTransDao;
import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.dao.base.LoginLogTransDao;
import cn.jianwoo.blog.dao.base.VisitTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.entity.Visit;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.service.biz.NetWorkService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private AdminTransDao adminTransDao;
    @Autowired
    private LoginLogTransDao loginLogTransDao;
    @Autowired
    private BizEventLogTransDao bizEventLogTransDao;

    public Future<String> execCommentIpAreaTask(Long oid) {
        log.info(">> Start async task execCommentIpAreaTask");
        try {
            Comment comment = commentTransDao.queryCommentByPrimaryKey(oid);
            if (StringUtils.isNotBlank(comment.getClientIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(comment.getClientIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(comment.getClientIp().trim());
                    Comment newComment = new Comment();
                    newComment.setOid(oid);
                    newComment.setUserArea(area);
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
            if (StringUtils.isNotBlank(visit.getVisitIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(visit.getVisitIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(visit.getVisitIp().trim());
                    Visit newVisit = new Visit();
                    newVisit.setOid(oid);
                    newVisit.setVisitArea(area);
                    visitTransDao.doUpdateByPrimaryKeySelective(newVisit);

                }
            }

        } catch (DaoException e) {
            log.error("jianwooTaskExecutor.execCommentIpAreaTask exec failed, e:", e);
        }
        log.info(">> End async task execVisitIpAreaTask");

        return new AsyncResult<>("execVisitIpAreaTask");
    }

    public Future<String> execAdminUserRegIpAreaTask(Long oid) {

        log.info(">> Start async task execAdminUserIpAreaTask");
        try {
            Admin admin = adminTransDao.queryAdminByPrimaryKey(oid);
            if (StringUtils.isNotBlank(admin.getRegisterIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(admin.getRegisterIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(admin.getRegisterIp().trim());
                    Admin newAdmin = new Admin();
                    newAdmin.setOid(oid);
                    newAdmin.setRegisterArea(area);
                    adminTransDao.doUpdateByPrimaryKeySelective(newAdmin);

                }
            }

        } catch (DaoException e) {
            log.error("jianwooTaskExecutor.execAdminUserIpAreaTask exec failed, e:", e);
        }
        log.info(">> End async task execAdminUserIpAreaTask");

        return new AsyncResult<>("execAdminUserIpAreaTask");
    }

    public Future<String> execAdminUserLoginIpAreaTask(Long oid) {

        log.info(">> Start async task execAdminUserLoginIpAreaTask");
        try {
            Admin admin = adminTransDao.queryAdminByPrimaryKey(oid);
            if (StringUtils.isNotBlank(admin.getLastLoginIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(admin.getLastLoginIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(admin.getLastLoginIp().trim());
                    Admin newAdmin = new Admin();
                    newAdmin.setOid(oid);
                    newAdmin.setRegisterArea(area);
                    adminTransDao.doUpdateByPrimaryKeySelective(newAdmin);

                }
            }

        } catch (DaoException e) {
            log.error("jianwooTaskExecutor.execAdminUserLoginIpAreaTask exec failed, e:", e);
        }
        log.info(">> End async task execAdminUserLoginIpAreaTask");

        return new AsyncResult<>("execAdminUserLoginIpAreaTask");
    }

    public Future<String> execLoginIpAreaTask(Long oid) {

        log.info(">> Start async task execLoginIpAreaTask");
        try {
            LoginLog loginLog = loginLogTransDao.queryLoginLogByPrimaryKey(oid);
            if (StringUtils.isNotBlank(loginLog.getTriggerIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(loginLog.getTriggerIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(loginLog.getTriggerIp().trim());
                    LoginLog newLoginLog = new LoginLog();
                    newLoginLog.setOid(oid);
                    newLoginLog.setTriggerArea(area);
                    loginLogTransDao.doUpdateByPrimaryKeySelective(newLoginLog);

                }
            }

        } catch (DaoException e) {
            log.error("jianwooTaskExecutor.execLoginIpAreaTask exec failed, e:", e);
        }
        log.info(">> End async task execLoginIpAreaTask");

        return new AsyncResult<>("execLoginIpAreaTask");
    }

}
