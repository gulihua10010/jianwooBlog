package cn.jianwoo.blog.controller.backend.page;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.config.router.admin.CommBackendPageTemplateConfig;
import cn.jianwoo.blog.config.router.admin.CommBackendPageUrlConfig;
import cn.jianwoo.blog.enums.PageIdEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-09 14:57
 */
@Controller()
@RequestMapping(CommBackendPageUrlConfig.URL_PREFIX)
@Slf4j
public class AdminPageController {

    /**
     * 首頁<br/>
     * url:/admin<br/>
     *
     * @return page /backend/index
     * @author gulihua
     */
    @PageId(PageIdEnum.ADMIN_INDEX)
    @RequestMapping
    public String main() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_INDEX;
    }

    /**
     * 首頁<br/>
     * url:/admin<br/>
     *
     * @return page /backend/index
     * @author gulihua
     */
    @PageId(PageIdEnum.ADMIN_INDEX)
    @RequestMapping(CommBackendPageUrlConfig.URL_INDEX)
    public String index() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_INDEX;
    }



}
