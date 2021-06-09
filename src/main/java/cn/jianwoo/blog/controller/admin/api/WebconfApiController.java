package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.router.admin.WebconfApiUrlConfig;
import cn.jianwoo.blog.dto.request.WebconfRequest;
import cn.jianwoo.blog.dto.response.WebconfResponse;
import cn.jianwoo.blog.dto.response.vo.WebconfVO;
import cn.jianwoo.blog.enums.ValidateTypeEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.WebconfBO;
import cn.jianwoo.blog.validation.BizValidation;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:40
 */
@RestController
@RequestMapping(WebconfApiUrlConfig.URL_PREFIX)
@Slf4j
public class WebconfApiController extends BaseController {
    @Autowired
    private WebconfBizService webconfBizService;


    /**
     * 文章配置更新(文章配置页面)<br/>
     * url:/api/admin/webconf/update<br/>
     *
     * @param param JSON 参数({@link WebconfRequest})
     *              list<br/>
     *              --key<br/>
     *              --desc<br/>
     *              --title<br/>
     *              --valueType<br/>
     *              --formType<br/>
     *              --value<br/>
     *              --mandatory<br/>
     *              --validateType<br/>
     *              --validateValue<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(WebconfApiUrlConfig.URL_WEBCONF_UPDATE)
    public String doUpdateWebconf(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            WebconfRequest request = this.convertParam(param, WebconfRequest.class);
            if (CollectionUtils.isNotEmpty(request.getList())) {
                List<WebconfBO> list = new ArrayList<>();
                for (WebconfVO o : request.getList()) {
                    validateReqWebconf(o);
                    WebconfBO bo = new WebconfBO();
                    BeanUtils.copyProperties(o, bo);

                    list.add(bo);
                }
                webconfBizService.doUpdateConfig(list);

            }


        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    private void validateReqWebconf(WebconfVO o) throws JwBlogException {
        if (null != o.getMandatory() && o.getMandatory()) {
            BizValidation.paramValidate(o.getValue(), o.getKey(), String.valueOf(o.getTitle()).concat("不能为空!"));

        }
        if (StringUtils.isNotBlank(o.getValidateType())) {
            String[] validateArr = o.getValidateType().split("\\|");
            JSONObject validateValue = null;
            try {
                validateValue = JSONObject.parseObject(o.getValidateValue());
            } catch (Exception e) {
                log.error("JSON validate Value parse failed, e:", e);
                return;
            }
            for (String valid : validateArr) {
                if (ValidateTypeEnum.MAX_LENGTH.getValue().equals(valid.trim())) {
                    JSONObject v = (JSONObject) validateValue.get(ValidateTypeEnum.MAX_LENGTH.getValue());
                    BizValidation.paramLengthValidate(o.getValue(), Integer.parseInt(v.getString("value")), o.getKey(),
                            String.format("字段 [%s] 的长度不能大于 %s !", o.getTitle(), o.getValidateValue()));
                }
                if (ValidateTypeEnum.MIN_NUM.getValue().equals(valid.trim())) {
                    JSONObject v = (JSONObject) validateValue.get(ValidateTypeEnum.MIN_NUM.getValue());
                    BizValidation.paramNumberMinValidate(o.getValue(), v.getString("value"), o.getKey(),
                            String.format("字段 [%s] 的值不能小于 %s !", o.getTitle(), v.getString("value")));
                }
                if (ValidateTypeEnum.MAX_NUM.getValue().equals(valid.trim())) {
                    JSONObject v = (JSONObject) validateValue.get(ValidateTypeEnum.MAX_NUM.getValue());
                    BizValidation.paramNumberMaxValidate(o.getValue(), v.getString("value"), o.getKey(),
                            String.format("字段 [%s] 的值不能大于 %s !", o.getTitle(), v.getString("value")));
                }
                if (ValidateTypeEnum.REGEX.getValue().equals(valid.trim())) {
                    JSONObject v = (JSONObject) validateValue.get(ValidateTypeEnum.REGEX.getValue());
                    BizValidation.paramRegexValidate(o.getValue(), v.getString("value"), o.getKey(),
                            String.format("正则 (%s) 不能匹配字段 [%s] 的值 %s!", v.getString("value"), o.getTitle(), o.getValue()));
                }
                if (ValidateTypeEnum.REGEX.getValue().equals(valid.trim())) {
                    BizValidation.paramNumberValidate(o.getValue(), o.getKey(),
                            String.format("字段 [%s] 的值不是数字类型!", o.getTitle()));
                }
            }

        }


    }

    /**
     * 获取前台首页菜单<br/>
     * url:/api/admin/webconf/info<br/>
     *
     * @return 返回响应 {@link WebconfResponse}
     * status<br/>
     * data<br/>
     * --key<br/>
     * --desc<br/>
     * --title<br/>
     * --valueType<br/>
     * --formType<br/>
     * --value<br/>
     * --mandatory<br/>
     * --validateType<br/>
     * --validateValue<br/>
     * @author gulihua
     */
    @GetMapping(WebconfApiUrlConfig.URL_WEBCONF_INFO)
    public String getWebConfig() {
        WebconfResponse response = WebconfResponse.getInstance();
        try {
            List<WebconfBO> webConflist = webconfBizService.queryConfig();
            List<WebconfVO> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(webConflist)) {
                webConflist.forEach(o -> {
                    WebconfVO vo = new WebconfVO();
                    BeanUtils.copyProperties(o, vo);
                    list.add(vo);

                });
            }

            response.setData(list);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

        return super.responseToJSONString(response);

    }


}
