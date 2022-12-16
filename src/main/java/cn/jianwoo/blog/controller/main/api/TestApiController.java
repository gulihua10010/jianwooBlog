package cn.jianwoo.blog.controller.main.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.service.biz.TestBizService;
import cn.jianwoo.blog.service.biz.impl.NetWorkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gulihua
 * @Description
 * @date 2022-10-22 17:59
 */
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestApiController extends BaseController {
    @Autowired
    private TestBizService testBizService;
    @Autowired
    NetWorkServiceImpl netWorkServiceImpl;

    @GetMapping
    @ApiVersion()
    public String test(@RequestBody String param) {
        log.info(">> exec test...");
        try {

            testBizService.test();
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

        return super.responseToJSONString(response);

    }

    @GetMapping("/ip")
    @ApiVersion()
    public String getIp(@RequestBody String param) {
        log.info(">> exec getIp...");
        try {

            String area = netWorkServiceImpl.getRegionFromXdb("117.89.213.238");

            return super.responseToJSONString(area);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }


    }
}
