package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.EmailTemplateApiUrlConfig;
import cn.jianwoo.blog.dto.request.EmailTplPageRequest;
import cn.jianwoo.blog.dto.request.EmailTplRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.EmailTplRenderResponse;
import cn.jianwoo.blog.dto.response.EmailTplResponse;
import cn.jianwoo.blog.dto.response.EmailTplSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.EmailTplSummaryVO;
import cn.jianwoo.blog.dto.response.vo.EmailTplVO;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.service.biz.EmailTplBizService;
import cn.jianwoo.blog.service.bo.EmailTplBO;
import cn.jianwoo.blog.service.param.EmailTplParam;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:10
 */
@RestController
@RequestMapping(EmailTemplateApiUrlConfig.URL_PREFIX)
@Slf4j
public class EmailTplApiController extends BaseController {
    @Autowired
    private EmailTplBizService emailTplBizService;


    /**
     * 分页查询评论(邮件模板列表)<br/>
     * url:/api/admin/email/tpl/query/list<br/>
     *
     * @param param JSON 参数({@link EmailTplPageRequest})
     *              title<br/>
     *              unread<br/>
     * @return 返回响应 {@link EmailTplSummaryResponse}
     * status<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --code<br/>
     * --desc<br/>
     * --subject<br/>
     * --createTimeDesc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(EmailTemplateApiUrlConfig.URL_EMAIL_TPL_QUERY_LIST)
    public String queryEmailTplPage(EmailTplPageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));
        EmailTplParam emailTplParam = new EmailTplParam();
        if (StringUtils.isNotBlank(param.getCode())) {
            emailTplParam.setCode(param.getCode());
        }
        if (StringUtils.isNotBlank(param.getDesc())) {
            emailTplParam.setDesc(param.getDesc());
        }
        emailTplParam.setPageNo(param.getPage());
        emailTplParam.setPageSize(param.getLimit());
        PageInfo<EmailTplBO> pageInfo = emailTplBizService.queryAllEmailTplPage(emailTplParam);

        EmailTplSummaryResponse response = EmailTplSummaryResponse.getInstance();
        List<EmailTplSummaryVO> list = new ArrayList<>();
        for (EmailTplBO emailTplBO : pageInfo.getList()) {
            EmailTplSummaryVO vo = JwBuilder.of(EmailTplSummaryVO::new)
                    .with(EmailTplSummaryVO::setOid, emailTplBO.getOid())
                    .with(EmailTplSummaryVO::setCode, emailTplBO.getEmailTplCode())
                    .with(EmailTplSummaryVO::setCreateTime, emailTplBO.getCreateTime())
                    .with(EmailTplSummaryVO::setCreateTimeStr, DateUtil.formatDateTime(emailTplBO.getCreateTime()))
                    .with(EmailTplSummaryVO::setDesc, emailTplBO.getDesc())
                    .with(EmailTplSummaryVO::setSubject, emailTplBO.getSubject()).build();
            list.add(vo);
        }
        response.setData(list);

        response.setCount(pageInfo.getTotal());
        return super.responseToJSONString(response);

    }


    /**
     * 创建邮件模板<br/>
     * url:/api/admin/email/tpl/create<br/>
     *
     * @param param JSON 参数({@link EmailTplRequest})
     *              code<br/>
     *              desc<br/>
     *              subject<br/>
     *              content<br/>
     *              testJsonData<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.EMAIL_TPL_CREATE)
    @SubToken
    @ApiVersion()
    @PostMapping(EmailTemplateApiUrlConfig.URL_EMAIL_TPL_CREATE)
    public String doCreateEmailTpl(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EmailTplRequest request = this.convertParam(param, EmailTplRequest.class);
            BizValidation.paramValidate(request.getCode(), "code", "邮件模板编号不能为空!");
            BizValidation.paramValidate(request.getSubject(), "subject", "邮件模板主题不能为空!");
            BizValidation.paramValidate(request.getContent(), "content", "邮件模板内容不能为空!");
            BizValidation.paramLengthValidate(request.getCode(), 20, "code", "邮件模板编号不能大于20个字符!");
            BizValidation.paramLengthValidate(request.getDesc(), 100, "subject", "邮件模板主题不能大于100个字符!");
            BizValidation.paramLengthValidate(request.getSubject(), 500, "content", "邮件模板内容不能大于500个字符!");
            EmailTplBO bo = JwBuilder.of(EmailTplBO::new)
                    .with(EmailTplBO::setEmailTplCode, request.getCode())
                    .with(EmailTplBO::setContent, request.getContent())
                    .with(EmailTplBO::setDesc, request.getDesc())
                    .with(EmailTplBO::setSubject, request.getSubject())
                    .with(EmailTplBO::setTestJsonData, request.getTestJsonData()).build();
            emailTplBizService.doCreateEmailTpl(bo);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 更新邮件模板<br/>
     * url:/api/admin/email/tpl/update<br/>
     *
     * @param param JSON 参数({@link EmailTplRequest})
     *              oid<br/>
     *              code<br/>
     *              desc<br/>
     *              subject<br/>
     *              content<br/>
     *              testJsonData<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.EMAIL_TPL_UPDATE)
    @SubToken
    @ApiVersion()
    @PostMapping(EmailTemplateApiUrlConfig.URL_EMAIL_TPL_UPDATE)
    public String doUpdateEmailTpl(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EmailTplRequest request = this.convertParam(param, EmailTplRequest.class);
            BizValidation.paramValidate(request.getOid(), "oid", "邮件模板oid不能为空!");
            BizValidation.paramValidate(request.getCode(), "code", "邮件模板编号不能为空!");
            BizValidation.paramValidate(request.getSubject(), "subject", "邮件模板主题不能为空!");
            BizValidation.paramValidate(request.getContent(), "content", "邮件模板内容不能为空!");
            BizValidation.paramLengthValidate(request.getCode(), 20, "code", "邮件模板编号不能大于20个字符!");
            BizValidation.paramLengthValidate(request.getDesc(), 100, "subject", "邮件模板主题不能大于100个字符!");
            BizValidation.paramLengthValidate(request.getSubject(), 500, "content", "邮件模板内容不能大于500个字符!");
            EmailTplBO bo = JwBuilder.of(EmailTplBO::new)
                    .with(EmailTplBO::setOid, request.getOid())
                    .with(EmailTplBO::setEmailTplCode, request.getCode())
                    .with(EmailTplBO::setContent, request.getContent())
                    .with(EmailTplBO::setDesc, request.getDesc())
                    .with(EmailTplBO::setSubject, request.getSubject())
                    .with(EmailTplBO::setTestJsonData, request.getTestJsonData()).build();
            emailTplBizService.doUpdateEmailTpl(bo);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 查询邮件模板信息<br/>
     * url:/api/admin/email/tpl/edit/{id}<br/>
     *
     * @param id id
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * oid<br/>
     * code<br/>
     * desc<br/>
     * subject<br/>
     * content<br/>
     * testJsonData<br/>
     * createTime<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(EmailTemplateApiUrlConfig.URL_EMAIL_TPL_EDIT)
    public String queryEmailTplEditInfo(@PathVariable("id") String id) {
        try {
            BizValidation.paramValidate(id, "id", "邮件模板id不能为空!");
            EmailTplBO emailTplBO = emailTplBizService.queryEmailTplByOid(id);
            EmailTplResponse response = EmailTplResponse.getInstance();
            EmailTplVO vo = JwBuilder.of(EmailTplVO::new)
                    .with(EmailTplVO::setOid, emailTplBO.getOid())
                    .with(EmailTplVO::setCode, emailTplBO.getEmailTplCode())
                    .with(EmailTplVO::setContent, emailTplBO.getContent())
                    .with(EmailTplVO::setDesc, emailTplBO.getDesc())
                    .with(EmailTplVO::setSubject, emailTplBO.getSubject())
                    .with(EmailTplVO::setTestJsonData, emailTplBO.getTestJsonData())
                    .with(EmailTplVO::setCreateTime, DateUtil.formatDateTime(emailTplBO.getCreateTime()))
                    .build();
            response.setData(vo);
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }

    /**
     * 删除邮件模板<br/>
     * url:/api/admin/email/tpl/remove<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(EmailTemplateApiUrlConfig.URL_EMAIL_TPL_REMOVE)
    public String doRemoveEmailTpl(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "邮件模板id不能为空!");
            emailTplBizService.doRemoveEmailTpl(request.getEntityOid());
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 渲染邮件模板<br/>
     * url:/api/admin/email/tpl/render<br/>
     *
     * @param param JSON 参数({@link EmailTplRequest})
     *              oid<br/>
     *              code<br/>
     *              desc<br/>
     *              subject<br/>
     *              contemt<br/>
     *              testJsonData<br/>
     *              createTime<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(EmailTemplateApiUrlConfig.URL_EMAIL_TPL_RENDER)
    public String doRenderEmailTpl(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EmailTplRequest request = this.convertParam(param, EmailTplRequest.class);
            BizValidation.paramValidate(request.getContent(), "content", "邮件模板内容不能为空!");
            BizValidation.paramValidate(request.getTestJsonData(), "testJsonData", "邮件模板测试json数据内容不能为空!");
            EmailTplBO bo = JwBuilder.of(EmailTplBO::new)
                    .with(EmailTplBO::setOid, request.getOid())
                    .with(EmailTplBO::setEmailTplCode, request.getCode())
                    .with(EmailTplBO::setContent, request.getContent())
                    .with(EmailTplBO::setDesc, request.getDesc())
                    .with(EmailTplBO::setSubject, request.getSubject())
                    .with(EmailTplBO::setTestJsonData, request.getTestJsonData()).build();
            String renderContent = emailTplBizService.doRenderEmailTpl(bo);
            EmailTplRenderResponse response = EmailTplRenderResponse.getInstance();
            response.setData(renderContent);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }

}
