package cn.jianwoo.blog.controller.main.api;

import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.AccessMainApiUrlConfig;
import cn.jianwoo.blog.dto.request.AnnouncePageRequest;
import cn.jianwoo.blog.dto.request.ArticleAccessRequest;
import cn.jianwoo.blog.dto.response.AnnounceSummaryResponse;
import cn.jianwoo.blog.service.biz.ArticleAccessBizService;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-11 09:44
 */
@RestController
@RequestMapping(AccessMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class ArticleAccessMainApiController extends BaseController {
    @Autowired
    private ArticleAccessBizService articleAccessBizService;


    /**
     * 访问文章(首页)<br/>
     * url:/api/access/article/read<br/>
     *
     * @param param JSON 参数({@link AnnouncePageRequest})<br/>
     *              artOid<br/>
     * @return 返回响应 {@link AnnounceSummaryResponse}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @PostMapping(AccessMainApiUrlConfig.URL_ACCESS_ARTICLE_READ)
    @ApiVersion()
    @IpLimit(key = "doRegisterAccessRecord")
    public String doRegisterAccessRecord(@RequestBody String param) {

        try {
            super.printRequestParams(param);
            ArticleAccessRequest req = this.convertParam(param, ArticleAccessRequest.class);
            BizValidation.paramValidate(req.getArtOid(), "artOid", "文章id不能为空!");
            articleAccessBizService.createAccessRecord(JwUtil.getRealIpAddress(request), Long.parseLong(req.getArtOid()));
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }
}
