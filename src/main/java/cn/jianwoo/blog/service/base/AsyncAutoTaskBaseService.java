package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.exception.JwBlogException;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-07 10:23
 */
public interface AsyncAutoTaskBaseService {

    /**
     * 创建任务
     *
     * @param taskType 任务类型
     * @param taskData 任务数据
     * @return
     * @author gulihua
     */
    Long doCreateTask(String taskType, String taskData) throws JwBlogException;


    /**
     * 更新任务
     *
     * @param taskId    任务ID
     * @param isSuccess 是否成功
     * @param code      失败代码
     * @param reason    失败原因
     * @return
     * @author gulihua
     */
    void doUpdateTask(Long taskId, boolean isSuccess, String code, String reason) throws JwBlogException;

    /**
     * 触发任务
     *
     * @param taskId 任务ID
     * @return
     * @author gulihua
     */
    void doTriggerTask(Long taskId);
}
