package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.service.biz.NetWorkService;
import cn.jianwoo.blog.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author GuLihua
 * @Description
 * @date 2020-12-16 18:31
 */
@Service
@Slf4j
public class NetWorkServiceImpl implements NetWorkService {
    private static final String QUERY_IP_URL = "https://www.ip.cn/api/index?ip=%s&type=1";

    @Override
    public String getIpArea(String ip) {
        try {
            BaseResponseDto responseDto = HttpClientUtil.doGet(String.format(QUERY_IP_URL, ip));
            if (responseDto.isSuccess()) {
                JSONObject jsonObject = JSON.parseObject(responseDto.getMsg());
                if (null != jsonObject) {
                    if (StringUtils.isNotBlank(jsonObject.getString("address"))) {
                        return jsonObject.getString("address");
                    }
                }

            }
        } catch (Exception e) {
            log.error("NetWorkServiceImpl.getAreaById exec failed: e\r\n", e);
            return Constants.UNKNOW;
        }
        return Constants.UNKNOW;

    }
}
