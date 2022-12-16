package cn.jianwoo.blog.task.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.jianwoo.blog.constants.TaskConstants;
import cn.jianwoo.blog.enums.AsyncIpEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.JwBlogTaskException;
import cn.jianwoo.blog.service.biz.NetWorkService;
import cn.jianwoo.blog.service.notify.NotifyMsgService;
import cn.jianwoo.blog.task.AsyncAutoTaskService;
import cn.jianwoo.blog.task.bo.TaskDataD0010BO;
import cn.jianwoo.blog.task.bo.TaskDataD0020BO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取IP地址所在的地区
 *
 * @author gulihua
 * @Description
 * @date 2022-05-07 13:46
 */
@Service("asyncProcAutoTaskD0020")
@Slf4j
public class AsyncProcAutoTaskD0020Impl implements AsyncAutoTaskService {
    private static final String IP_REX_PATTERN = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
    @Autowired
    private NetWorkService netWorkService;

    @Override
    public JSONObject doProc(Long taskId, String taskData) throws JwBlogTaskException {

        log.info(">> start D0020 task({}) which data is {} <<", taskId, taskData);

        // read parameter taskData
        TaskDataD0020BO data = JSON.parseObject(taskData, TaskDataD0020BO.class);
        AsyncIpEnum asyncIpEnum = AsyncIpEnum.getEnum(data.getAsyncIpType());

        try {
            if (StringUtils.isNotBlank(data.getIp())) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(data.getIp().trim());
                if (m.matches()) {
                    String area = netWorkService.getIpRegion(data.getIp().trim());
                    Object o = asyncIpEnum.getClazz().newInstance();
                    BeanUtil.setFieldValue(o, asyncIpEnum.getPrimaryKey(), data.getOid());
                    BeanUtil.setFieldValue(o, asyncIpEnum.getField(), area);
                    Object svcBean = SpringUtil.getBean(asyncIpEnum.getSvcBean());
                    MethodUtils.invokeMethod(svcBean, "doUpdateByPrimaryKeySelective", o);

                }
            }

        } catch (Exception e) {
            log.error("AsyncProcAutoTaskD0020Impl.doProc exec failed, e:", e);
            throw new JwBlogTaskException(TaskConstants.FAILED_CODE, e.getMessage());
        }
        return returnSuccessJsonResult();
    }

    private JSONObject returnSuccessJsonResult() {
        JSONObject jsonRlt = new JSONObject();
        jsonRlt.put(TaskConstants.RESULT_CODE, TaskConstants.SUCCESS_CODE);
        jsonRlt.put(TaskConstants.RESULT_MSG, TaskConstants.SUCCESS_MSG);
        return jsonRlt;
    }
}
