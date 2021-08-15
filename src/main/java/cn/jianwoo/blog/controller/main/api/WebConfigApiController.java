package cn.jianwoo.blog.controller.main.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.WebConfigApiUrlConfig;
import cn.jianwoo.blog.dao.base.WebconfTransDao;
import cn.jianwoo.blog.dto.request.WebConfigCommRequest;
import cn.jianwoo.blog.dto.request.WebconfRequest;
import cn.jianwoo.blog.dto.response.WebConfigCommResponse;
import cn.jianwoo.blog.dto.response.vo.WebConfigVO;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private WebconfTransDao webconfTransDao;


    /**
     * 查询网站配置<br/>
     * url:/api/main/config/query<br/>
     *
     * @param param JSON 参数({@link WebconfRequest})
     *              key
     * @return 返回响应 {@link WebConfigCommResponse}
     * status<br/>
     * data<br/>
     * --value<br/> \
     * @author gulihua
     */
    @PostMapping(WebConfigApiUrlConfig.URL_CONFIG_QUERY)
    @ApiVersion()
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


}
