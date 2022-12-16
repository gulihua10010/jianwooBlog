package cn.jianwoo.blog.controller.main.api;

import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.WebConfigApiUrlConfig;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dto.request.WebConfigCommRequest;
import cn.jianwoo.blog.dto.request.WebconfRequest;
import cn.jianwoo.blog.dto.response.WebConfigCommResponse;
import cn.jianwoo.blog.dto.response.WebConfigPageResponse;
import cn.jianwoo.blog.dto.response.vo.WebConfigVO;
import cn.jianwoo.blog.dto.response.vo.WebPageConfigVO;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:40
 */
@RestController
@RequestMapping(WebConfigApiUrlConfig.URL_PREFIX)
@Slf4j
public class WebConfigApiController extends BaseController {
    @Autowired
    private WebconfBizService webconfBizService;


    /**
     * 查询网站配置<br/>
     * url:/api/config/query<br/>
     *
     * @param param JSON 参数({@link WebconfRequest})<br/>
     *              key
     * @return 返回响应 {@link WebConfigCommResponse}<br/>
     * status<br/>
     * data<br/>
     * --value<br/> \
     * @author gulihua
     */
    @PostMapping(WebConfigApiUrlConfig.URL_CONFIG_QUERY)
    @ApiVersion()
    @IpLimit(key = "getWebConfig")
    public String getWebConfig(@RequestBody String param) {
        WebConfigCommResponse response = WebConfigCommResponse.getInstance();
        try {
            super.printRequestParams(param);
            WebConfigCommRequest request = this.convertParam(param, WebConfigCommRequest.class);
            BizValidation.paramValidate(request.getKey(), "key", "KEY不能为空!");
            WebConfigVO webConfigVO = new WebConfigVO();
            webConfigVO.setValue(webconfBizService.queryWebconfByKey(request.getKey()));
            response.setData(webConfigVO);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

        return super.responseToJSONString(response);

    }

    /**
     * 查询基础网站配置<br/>
     * url:/api/config/page/comm/query<br/>
     *
     * @return 返回响应 {@link WebConfigCommResponse}<br/>
     * status<br/>
     * data<br/>
     * --title<br/>
     * --keywords<br/>
     * --desc<br/>
     * --author<br/>
     * @author gulihua
     */
    @GetMapping(WebConfigApiUrlConfig.URL_CONFIG_PAGE_COMM_QUERY)
    @ApiVersion()
    @IpLimit(key = "getWebConfigPage")
    public String getWebCommConfigPage() {
        WebConfigPageResponse response = WebConfigPageResponse.getInstance();
        try {
            WebPageConfigVO webConfigVO = new WebPageConfigVO();
            webConfigVO.setTitle(webconfBizService.queryWebconfByKey(WebConfDataConfig.TITLE));
            webConfigVO.setKeywords(webconfBizService.queryWebconfByKey(WebConfDataConfig.KEYWORDS));
            webConfigVO.setDesc(webconfBizService.queryWebconfByKey(WebConfDataConfig.DESCRIPTION));
            webConfigVO.setAuthor(webconfBizService.queryWebconfByKey(WebConfDataConfig.AUTHOR));
            response.setData(webConfigVO);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

        return super.responseToJSONString(response);

    }


    /**
     * 查询网站页脚配置<br/>
     * url:/api/config/page/footer/query<br/>
     *
     * @return 返回响应 {@link WebConfigCommResponse}<br/>
     * status<br/>
     * data<br/>
     * --footHtml<br/>
     * --record<br/>
     * --recordUrl<br/>
     * --domain<br/>
     * --title<br/>
     * @author gulihua
     */
    @GetMapping(WebConfigApiUrlConfig.URL_CONFIG_PAGE_FOOTER_QUERY)
    @ApiVersion()
    @IpLimit(key = "getWebFooterConfigPage")
    public String getWebFooterConfigPage() {
        WebConfigPageResponse response = WebConfigPageResponse.getInstance();
        try {
            WebPageConfigVO webConfigVO = new WebPageConfigVO();
            webConfigVO.setFootHtml(webconfBizService.queryWebconfByKey(WebConfDataConfig.FOOT_HTML));
            webConfigVO.setRecord(webconfBizService.queryWebconfByKey(WebConfDataConfig.RECORD));
            webConfigVO.setRecordUrl(webconfBizService.queryWebconfByKey(WebConfDataConfig.RECORD_URL));
            webConfigVO.setDomain(webconfBizService.queryWebconfByKey(WebConfDataConfig.DOMAIN));
            webConfigVO.setTitle(webconfBizService.queryWebconfByKey(WebConfDataConfig.TITLE));
            response.setData(webConfigVO);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

        return super.responseToJSONString(response);

    }

}
