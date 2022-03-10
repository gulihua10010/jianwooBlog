package cn.jianwoo.blog.controller.admin.page;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.config.router.admin.CommAdminPageTemplateConfig;
import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
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
@RequestMapping(CommAdminPageUrlConfig.URL_PREFIX)
@Slf4j
public class AdminPageController {

    /**
     * 首頁<br/>
     * url:/admin<br/>
     *
     * @return page /admin/index
     * @author gulihua
     */
    @PageId(PageIdEnum.ADMIN_INDEX)
    @RequestMapping
    public String main() {
        return CommAdminPageTemplateConfig.PAGE_PREFIX + CommAdminPageTemplateConfig.PAGE_INDEX;
    }

    /**
     * 首頁<br/>
     * url:/admin<br/>
     *
     * @return page /admin/index
     * @author gulihua
     */
    @PageId(PageIdEnum.ADMIN_INDEX)
    @RequestMapping(CommAdminPageUrlConfig.URL_INDEX)
    public String index() {
        return CommAdminPageTemplateConfig.PAGE_PREFIX + CommAdminPageTemplateConfig.PAGE_INDEX;
    }

    /**
     * passport<br/>
     * url:/admin/passport<br/>
     *
     * @return page /admin/passport
     * @author gulihua
     */
    @PageId(PageIdEnum.PASSPORT)
    @RequestMapping(CommAdminPageUrlConfig.URL_PASSPORT)
    public String passport() {
        return CommAdminPageTemplateConfig.PAGE_PREFIX + CommAdminPageTemplateConfig.PAGE_PASSPORT;
    }


}
