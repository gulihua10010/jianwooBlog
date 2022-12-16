package cn.jianwoo.blog.task;

import cn.jianwoo.blog.exception.JwBlogTaskException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-07 11:37
 */
public interface AsyncAutoTaskService {

    /**
     * 处理自动任务
     *
     * @param taskId   任务id
     * @param taskData 任务数据
     * @return
     * @author gulihua
     */
    JSONObject doProc(Long taskId, String taskData) throws JwBlogTaskException;
}
