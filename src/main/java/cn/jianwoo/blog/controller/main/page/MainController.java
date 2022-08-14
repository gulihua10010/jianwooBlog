package cn.jianwoo.blog.controller.main.page;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.router.main.CommMainPageTemplateConfig;
import cn.jianwoo.blog.config.router.main.CommMainPageUrlConfig;
import cn.jianwoo.blog.util.JwUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 *
 * @author gulihua
 * @date 2022-08-04 11:09
 */
@Controller()
@RequestMapping(CommMainPageUrlConfig.URL_PREFIX)
@Slf4j
public class MainController extends BaseController {

    /**
     * 首頁<br/>
     * url:/<br/>
     *
     * @return page /main/jianwoo/dist
     * @author gulihua
     */
    @RequestMapping
    public String main() {
        return CommMainPageTemplateConfig.PAGE_PREFIX + CommMainPageTemplateConfig.PAGE_INDEX;
    }

    /**
     * 首頁<br/>
     * url:/index<br/>
     *
     * @return page /main/jianwoo/dist
     * @author gulihua
     */
    @RequestMapping(CommMainPageUrlConfig.URL_INDEX)
    public String index() {
        return CommMainPageTemplateConfig.PAGE_PREFIX + CommMainPageTemplateConfig.PAGE_INDEX;
    }


    /**
     * 兼容原先php 的简窝项目<br/>
     * 老的简窝的明细页面地址<br/>
     * url:/detailid/{id}.html<br/>
     *
     * @author gulihua
     */
    @RequestMapping(value = CommMainPageUrlConfig.URL_OLD_PHP_DETAIL, method = RequestMethod.GET)
    public String oldDetail(@PathVariable("id") String id) {
        log.info(">>redirect request, ip = [{}], id=[{}]", JwUtil.getRealIpAddress(request), id);
        return String.format(CommMainPageUrlConfig.URL_NEW_VUE_DETAIL_ROUTE, id);
    }

}
