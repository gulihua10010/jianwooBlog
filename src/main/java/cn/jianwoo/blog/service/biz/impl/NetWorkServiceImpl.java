package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.service.biz.NetWorkService;
import cn.jianwoo.blog.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author GuLihua
 * @Description
 * @date 2020-12-16 18:31
 */
@Service
@Slf4j
public class NetWorkServiceImpl implements NetWorkService {
    @Value("${query.ip.url}")
    private String queryIpUrl;

    @Override
    public String getIpRegion(String ip) {

        String region = getRegionFromXdb(ip);
        if (Constants.UNKNOW.equals(region)) {
            region = getRegionFromApi(ip);
        }
        return region;

    }

    public String getRegionFromApi(String ip) {

        try {
            BaseResponseDto responseDto = HttpClientUtil.doGet(String.format(queryIpUrl, ip));
            if (responseDto.isSuccess()) {
                JSONObject jsonObject = JSON.parseObject(responseDto.getMsg());
                if (null != jsonObject) {
                    return format4Api(ip, jsonObject);

                }

            }
        } catch (Exception e) {
            log.error("NetWorkServiceImpl.getRegionFromApi exec failed: e\r\n", e);
            return Constants.UNKNOW;
        }
        return Constants.UNKNOW;
    }

    public String getRegionFromXdb(String ip) {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            org.springframework.core.io.Resource[] resources = resolver.getResources("ip/ip2region.xdb");
            InputStream inputStream = resources[0].getInputStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            Searcher searcher = null;
            try {
                searcher = Searcher.newWithBuffer(result.toByteArray());
            } catch (IOException e) {
                log.error(String.format("failed to create searcher with `ip/ip2region.xdb`: %s\n", e));
                return Constants.UNKNOW;

            }

            // 2、查询
            try {
                long sTime = System.nanoTime();
                String region = searcher.search(ip);
                long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
                log.error(String.format("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost));
                return format4Xdb(region);
            } catch (Exception e) {
                log.error(String.format("failed to search(%s): \n", ip), e);
                return Constants.UNKNOW;
            }
        } catch (Exception e) {
            log.error("NetWorkServiceImpl.getRegionFromXdb exec failed: e\r\n", e);
            return Constants.UNKNOW;
        }
    }


    private String format4Xdb(String region) {
        if (StringUtils.isBlank(region)) {
            return Constants.UNKNOW;
        }
        if (region.contains("内网")) {
            return "内网IP";
        }
        String[] arr = region.split("\\|");
        if (region.startsWith("中国")) {
            if (arr.length > 4) {
                return arr[2] + arr[3];
            }
            return region;
        }
        if (arr.length > 0) {
            return arr[0];
        }
        return region;
    }

    private String format4Api(String ip, JSONObject regionJson) {
        String region = Constants.UNKNOW;
        if (null == regionJson) {
            return region;
        }
        if (ip.equals("127.0.0.1")) {
            return "内网IP";
        }
        JSONArray data = regionJson.getJSONArray("data");
        if (data != null && data.size() > 0) {
            JSONObject data0 = data.getJSONObject(0);
            if (null != data0) {
                region = data0.getString("location");
                String[] arr = region.split(" ");
                if (arr.length > 0) {
                    return arr[0];
                }

            }
        }


        return region;
    }
}
