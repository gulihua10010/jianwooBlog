package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.EmailApiUrlConfig;
import cn.jianwoo.blog.dto.request.EmailPageRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.EmailResponse;
import cn.jianwoo.blog.dto.response.EmailSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.EmailSummaryVO;
import cn.jianwoo.blog.dto.response.vo.EmailVO;
import cn.jianwoo.blog.service.biz.EmailBizService;
import cn.jianwoo.blog.service.bo.EmailBO;
import cn.jianwoo.blog.service.param.EmailParam;
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
@RequestMapping(EmailApiUrlConfig.URL_PREFIX)
@Slf4j
public class EmailApiController extends BaseController {
    @Autowired
    private EmailBizService emailBizService;


    /**
     * 分页查询邮件(邮件列表)<br/>
     * url:/api/admin/email/query/list<br/>
     *
     * @param param JSON 参数({@link EmailPageRequest})<br/>
     *              code<br/>
     *              subject<br/>
     *              exception<br/>
     *              failed<br/>
     * @return 返回响应 {@link EmailSummaryResponse}<br/>
     * status<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --emailTos<br/>
     * --code<br/>
     * --subject<br/>
     * --processStatus<br/>
     * --sendTimeDesc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(EmailApiUrlConfig.URL_EMAIL_QUERY_LIST)
    public String queryEmailPage(EmailPageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));
            EmailParam emailTplParam = new EmailParam();
            if (StringUtils.isNotBlank(param.getCode())) {
                emailTplParam.setCode(param.getCode());
            }
            if (StringUtils.isNotBlank(param.getSubject())) {
                emailTplParam.setSubject(param.getSubject());
            }
            if (StringUtils.isNotBlank(param.getFailed())) {
                emailTplParam.setFailed(param.getFailed());
            }
            if (StringUtils.isNotBlank(param.getException())) {
                emailTplParam.setException(param.getException());
            }
            emailTplParam.setPageNo(param.getPage());
            emailTplParam.setPageSize(param.getLimit());
            PageInfo<EmailBO> pageInfo = emailBizService.queryAllEmailListPage(emailTplParam);

            EmailSummaryResponse response = EmailSummaryResponse.getInstance();
            List<EmailSummaryVO> list = new ArrayList<>();
            for (EmailBO emailBO : pageInfo.getList()) {
                EmailSummaryVO vo = JwBuilder.of(EmailSummaryVO::new)
                        .with(EmailSummaryVO::setOid, emailBO.getOid())
                        .with(EmailSummaryVO::setEmailTos, emailBO.getToEmail())
                        .with(EmailSummaryVO::setCode, emailBO.getEmailTplCode())
                        .with(EmailSummaryVO::setSendTime, emailBO.getCreateTime())
                        .with(EmailSummaryVO::setProcessStatus, emailBO.getProcStatus())
                        .with(EmailSummaryVO::setSendTimeStr, DateUtil.formatDateTime(emailBO.getCreateTime()))
                        .with(EmailSummaryVO::setSubject, emailBO.getSubject()).build();
                list.add(vo);
            }
            response.setData(list);

            response.setCount(pageInfo.getTotal());
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 查询邮件信息<br/>
     * url:/api/admin/email/view/{id}<br/>
     *
     * @param id id
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * oid<br/>
     * emailTos<br/>
     * code<br/>
     * subject<br/>
     * content<br/>
     * sendTime<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(EmailApiUrlConfig.URL_EMAIL_VIEW)
    public String queryEmailTplEditInfo(@PathVariable("id") String id) {
        try {
            BizValidation.paramValidate(id, "id", "邮件模板id不能为空!");
            EmailBO emailBO = emailBizService.queryEmailDetail(id);
            EmailResponse response = EmailResponse.getInstance();
            EmailVO vo = JwBuilder.of(EmailVO::new)
                    .with(EmailVO::setOid, emailBO.getOid())
                    .with(EmailVO::setCode, emailBO.getEmailTplCode())
                    .with(EmailVO::setContent, emailBO.getContent())
                    .with(EmailVO::setEmailTos, emailBO.getToEmail())
                    .with(EmailVO::setSubject, emailBO.getSubject())
                    .with(EmailVO::setSendTime, DateUtil.formatDateTime(emailBO.getCreateTime()))
                    .build();
            response.setData(vo);
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 邮件重发<br/>
     * url:/api/admin/email/resend<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(EmailApiUrlConfig.URL_EMAIL_RESEND)
    public String doResendEmail(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "邮件模板id不能为空!");

            emailBizService.doReSend(request.getEntityOid());
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 邮件重发<br/>
     * url:/api/admin/email/resend/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})<br/>
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(EmailApiUrlConfig.URL_EMAIL_RESEND_LIST)
    public String doResendEmailList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "邮件模板id 列表不能为空!");

            emailBizService.doReSendList(request.getEntityOidList());
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }

}
