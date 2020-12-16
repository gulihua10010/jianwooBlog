package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.service.biz.NetWorkService;
import cn.jianwoo.blog.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
public class NetWorkServiceImpl implements NetWorkService {
    private static final Logger logger = LoggerFactory.getLogger(NetWorkServiceImpl.class);
    private static final String QUERY_IP_URL = "https://www.ip.cn/api/index?ip=%s&type=1";

    @Override
    public String getIpArea(String ip) {
        try {
            String res = HttpClientUtil.doGet(String.format(QUERY_IP_URL, ip));
            if (StringUtils.isNotBlank(res)) {
                JSONObject jsonObject = JSON.parseObject(res);
                if (null != jsonObject) {
                    if (StringUtils.isNotBlank(jsonObject.getString("address"))) {
                        return jsonObject.getString("address");
                    }
                }

            }
        } catch (Exception e) {
            logger.error("NetWorkServiceImpl.getAreaById exec failed: e\r\n", e);
            return Constants.UNKNOW;
        }
        return Constants.UNKNOW;

    }
}
