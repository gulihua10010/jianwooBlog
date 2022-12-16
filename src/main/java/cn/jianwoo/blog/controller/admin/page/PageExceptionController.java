package cn.jianwoo.blog.controller.admin.page;

import cn.jianwoo.blog.config.router.admin.CommAdminPageTemplateConfig;
import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-24 16:37
 */
@Slf4j
@Controller
public class PageExceptionController implements ErrorController {


    @RequestMapping(CommAdminPageUrlConfig.URL_NOT_FOUND)
    public String notFound(HttpServletRequest request) {
        return CommAdminPageTemplateConfig.PAGE_PREFIX + CommAdminPageTemplateConfig.PAGE_404;
    }

    @RequestMapping(CommAdminPageUrlConfig.URL_ERROR)
    public String handleException(HttpServletRequest request) {
        return CommAdminPageTemplateConfig.PAGE_PREFIX + CommAdminPageTemplateConfig.PAGE_ERROR;

    }

    @Override
    public String getErrorPath() {
        return CommAdminPageUrlConfig.URL_ERROR;
    }
}
