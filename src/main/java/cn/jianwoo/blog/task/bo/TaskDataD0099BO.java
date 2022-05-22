package cn.jianwoo.blog.task.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-07 14:21
 */

public class TaskDataD0099BO implements Serializable {
    /**
     * 异常消息
     */
    private String excepionMsg;

    public String getExcepionMsg() {
        return this.excepionMsg;
    }

    public void setExcepionMsg(String excepionMsg) {
        if (!StringUtils.isBlank(excepionMsg)) {
            if (excepionMsg.length() > 1900) {
                this.excepionMsg = excepionMsg.substring(0, 1900);

            } else {
                this.excepionMsg = excepionMsg;
            }

        }
    }
}
