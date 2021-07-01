package cn.jianwoo.blog.quartz;

import cn.jianwoo.blog.exception.JwBlogException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author gulihua
 */
@Slf4j
public abstract class AbstractBizJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            process();
        } catch (JwBlogException e) {
            log.error(">>Job exe failed, e\r\n", e);
        }

    }

    protected abstract void process() throws JwBlogException;

}
