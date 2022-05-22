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
public class TaskDataD0020BO implements Serializable {
    /**
     * 主键
     */
    private Long oid;
    /**
     * IP
     */
    private String ip;
    /**
     * 类型
     */
    private String asyncIpType;
}
