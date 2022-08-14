package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.service.biz.NetWorkService;
import cn.jianwoo.blog.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
                    return format4Api(jsonObject.getString("address"));

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
            ClassPathResource classPathResource = new ClassPathResource("classpath:ip/ip2region.xdb");
            String dbPath = classPathResource.getPath();
            log.info("getRegionFromXdb path :{}", dbPath);
            Searcher searcher = null;
            try {
                searcher = Searcher.newWithFileOnly(dbPath);
            } catch (IOException e) {
                log.error(String.format("failed to create searcher with `%s`: %s\n", dbPath, e));
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

    private String format4Api(String region) {
        if (StringUtils.isBlank(region)) {
            return Constants.UNKNOW;
        }
        if (region.contains("内网")) {
            return "内网IP";
        }

        String[] arr = region.split(" ");
        List<String> list = Arrays.asList(arr).stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (region.startsWith("中国")) {
            if (list.size() > 2) {
                return list.get(1) + list.get(2);
            }
            return region;
        }

        if (list.size() > 1) {
            return list.get(0);
        }

        return region;
    }
}
