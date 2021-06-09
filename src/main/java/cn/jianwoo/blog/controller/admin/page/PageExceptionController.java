package cn.jianwoo.blog.controller.admin.page;

import cn.jianwoo.blog.config.router.admin.CommAdminPageTemplateConfig;
import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
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


    @RequestMapping(CommAdminPageUrlConfig.URL_ERROR)
    public String handleException(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return CommAdminPageTemplateConfig.PAGE_PREFIX + CommAdminPageTemplateConfig.PAGE_404;

        } else {
            return CommAdminPageTemplateConfig.PAGE_PREFIX + CommAdminPageTemplateConfig.PAGE_ERROR;

        }


    }


    @Override
    public String getErrorPath() {
        return CommAdminPageUrlConfig.URL_ERROR;
    }
}
