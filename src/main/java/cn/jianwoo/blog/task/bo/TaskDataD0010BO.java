package cn.jianwoo.blog.task.bo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-07 14:21
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class TaskDataD0010BO implements Serializable {
    /**
     * 邮件模板编码
     */
    private String emailTplCode;
    /**
     * 收信人
     */
    private String recipient;
    /**
     * 参数
     */
    private JSONObject param;
}
