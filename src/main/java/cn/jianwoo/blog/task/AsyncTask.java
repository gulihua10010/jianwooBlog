package cn.jianwoo.blog.task;

import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.service.biz.NetWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author GuLihua
 * @Description
 * @date 2020-12-16 18:10
 */
@Component
@Async("jianwooTaskExecutor")
public class AsyncTask {
    @Autowired
    private NetWorkService netWorkService;
    @Autowired
    private CommentTransDao commentTransDao;

    public Future<String> getIpArea() {

        return new AsyncResult<>("getIpArea");
    }
}
