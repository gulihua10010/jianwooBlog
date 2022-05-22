package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.AnnounceApiUrlConfig;
import cn.jianwoo.blog.dto.request.AnnouncePageRequest;
import cn.jianwoo.blog.dto.request.AnnounceRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.AnnounceResponse;
import cn.jianwoo.blog.dto.response.AnnounceSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.AnnounceSummaryVO;
import cn.jianwoo.blog.dto.response.vo.AnnounceVO;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.service.biz.AnnouncementBizService;
import cn.jianwoo.blog.service.bo.AnnounceBO;
import cn.jianwoo.blog.service.param.AnnounceParam;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-31 17:11
 */
@RestController
@RequestMapping(AnnounceApiUrlConfig.URL_PREFIX)
@Slf4j
public class AnnounceApiController extends BaseController {

    @Autowired
    private AnnouncementBizService announcementBizService;


    /**
     * 分页查询公告(公告列表)<br/>
     * url:/api/admin/announcement/query/list<br/>
     *
     * @param param JSON 参数({@link AnnouncePageRequest})<br/>
     *              title<br/>
     * @return 返回响应 {@link AnnounceSummaryResponse}<br/>
     * status<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --pushBy<br/>
     * --pushTime<br/>
     * --expiationTime<br/>
     * --status<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_QUERY_LIST)
    public String queryAnnouncePage(AnnouncePageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));
            AnnounceParam announceParam = new AnnounceParam();
            if (StringUtils.isNotBlank(param.getTitle())) {
                announceParam.setTitle(param.getTitle());
            }
            if (StringUtils.isNotBlank(param.getStatus())) {
                announceParam.setStatus(param.getStatus());
            }
            announceParam.setPageNo(param.getPage());
            announceParam.setPageSize(param.getLimit());
            PageInfo<AnnounceBO> pageInfo = announcementBizService.queryAllAnnouncePage(announceParam);

            AnnounceSummaryResponse response = AnnounceSummaryResponse.getInstance();
            List<AnnounceSummaryVO> list = new ArrayList<>();
            for (AnnounceBO emailTplBO : pageInfo.getList()) {
                AnnounceSummaryVO vo = JwBuilder.of(AnnounceSummaryVO::new)
                        .with(AnnounceSummaryVO::setOid, emailTplBO.getOid())
                        .with(AnnounceSummaryVO::setTitle, emailTplBO.getTitle())
                        .with(AnnounceSummaryVO::setExpiationTime, DateUtil.formatDateTime(emailTplBO.getExpiationTime()))
                        .with(AnnounceSummaryVO::setCreateTime, DateUtil.formatDateTime(emailTplBO.getCreateTime()))
                        .with(AnnounceSummaryVO::setPushTimeStr, DateUtil.formatDateTime(emailTplBO.getPushTime()))
                        .with(AnnounceSummaryVO::setPushTime, emailTplBO.getPushTime())
                        .with(AnnounceSummaryVO::setPushBy, emailTplBO.getPushBy())
                        .with(AnnounceSummaryVO::setStatus, emailTplBO.getStatus())
                        .build();
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
     * 创建公告<br/>
     * url:/api/admin/announcement/create<br/>
     *
     * @param param JSON 参数({@link AnnounceRequest})<br/>
     *              title<br/>
     *              content<br/>
     *              pushBy<br/>
     *              expiationTime<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ANNOUNCEMENT_CREATE)
    @SubToken
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_CREATE)
    public String doCreateAnnounce(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            AnnounceRequest request = this.convertParam(param, AnnounceRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "公告标题不能为空!");
            BizValidation.paramValidate(request.getContent(), "content", "公告内容不能为空!");
            BizValidation.paramValidate(request.getExpiationTime(), "expiationTime", "过期时间不能为空!");
            BizValidation.paramDateTimeValidate(request.getExpiationTime(), "expiationTime", "过期时间格式(yyyy-MM-dd HH:mm:ss)不正确!");
            BizValidation.paramDateRangeValidate(request.getExpiationTime(), "yyyy-MM-dd HH:mm:ss", cn.jianwoo.blog.util.DateUtil.getNow(), null, "expiationTime", "过期时间格式必须大于当前时间!");
            BizValidation.paramLengthValidate(request.getTitle(), 50, "title", "公告标题不能大于50个字符!");
            AnnounceBO bo = JwBuilder.of(AnnounceBO::new)
                    .with(AnnounceBO::setTitle, request.getTitle())
                    .with(AnnounceBO::setContent, request.getContent())
                    .with(AnnounceBO::setExpiationTimeStr, request.getExpiationTime()).build();
            announcementBizService.doCreateAnnounce(bo);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 更新公告<br/>
     * url:/api/admin/announcement/update<br/>
     *
     * @param param JSON 参数({@link AnnounceRequest})<br/>
     *              oid<br/>
     *              title<br/>
     *              content<br/>
     *              pushBy<br/>
     *              expiationTime<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ANNOUNCEMENT_UPDATE)
    @SubToken
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_UPDATE)
    public String doUpdateAnnounce(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            AnnounceRequest request = this.convertParam(param, AnnounceRequest.class);
            BizValidation.paramValidate(request.getOid(), "oid", "公告oid不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "公告标题不能为空!");
            BizValidation.paramValidate(request.getContent(), "content", "公告内容不能为空!");
            BizValidation.paramValidate(request.getExpiationTime(), "expiationTime", "过期时间不能为空!");
            BizValidation.paramDateTimeValidate(request.getExpiationTime(), "expiationTime", "过期时间格式(yyyy-MM-dd HH:mm:ss)不正确!");
            BizValidation.paramDateRangeValidate(request.getExpiationTime(), "yyyy-MM-dd HH:mm:ss", cn.jianwoo.blog.util.DateUtil.getNow(), null, "expiationTime", "过期时间格式必须大于当前时间!");
            BizValidation.paramLengthValidate(request.getTitle(), 50, "title", "公告标题不能大于50个字符!");
            AnnounceBO bo = JwBuilder.of(AnnounceBO::new)
                    .with(AnnounceBO::setOid, request.getOid())
                    .with(AnnounceBO::setTitle, request.getTitle())
                    .with(AnnounceBO::setContent, request.getContent())
                    .with(AnnounceBO::setExpiationTimeStr, request.getExpiationTime()).build();
            announcementBizService.doUpdateAnnounce(bo);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 查询公告信息<br/>
     * url:/api/admin/announcement/edit/{id}<br/>
     *
     * @param id id
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * title<br/>
     * content<br/>
     * pushBy<br/>
     * pushTime<br/>
     * expiationTime<br/>
     * status<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_EDIT)
    public String queryAnnounceEditInfo(@PathVariable("id") String id) {
        try {
            BizValidation.paramValidate(id, "id", "公告id不能为空!");
            AnnounceBO announceBO = announcementBizService.queryAnnounceByOid(id);
            AnnounceResponse response = AnnounceResponse.getInstance();
            AnnounceVO vo = JwBuilder.of(AnnounceVO::new)
                    .with(AnnounceVO::setOid, announceBO.getOid())
                    .with(AnnounceVO::setTitle, announceBO.getTitle())
                    .with(AnnounceVO::setContent, announceBO.getContent())
                    .with(AnnounceVO::setPushBy, announceBO.getPushBy())
                    .with(AnnounceVO::setExpiationTime, DateUtil.formatDateTime(announceBO.getExpiationTime()))
                    .with(AnnounceVO::setPushTime, DateUtil.formatDateTime(announceBO.getPushTime()))
                    .build();
            response.setData(vo);
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 删除公告<br/>
     * url:/api/admin/announcement/remove<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOid
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_REMOVE)
    public String doRemoveAnnounce(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "公告id不能为空!");
            announcementBizService.doRemoveAnnounce(request.getEntityOid());
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 作废公告<br/>
     * url:/api/admin/announcement/void<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOid
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_VOID)
    public String doVoidAnnounce(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "公告id不能为空!");
            announcementBizService.doVoidAnnounce(request.getEntityOid());
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 还原公告<br/>
     * url:/api/admin/announcement/revert<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOid
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_REVERT)
    public String doRevertAnnounce(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "公告id不能为空!");
            announcementBizService.doRevertAnnounce(request.getEntityOid());
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 批量作废公告<br/>
     * url:/api/admin/announcement/list/void<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOidList
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_VOID_LIST)
    public String doVoidAnnounceList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "公告id列表不能为空!");
            announcementBizService.doVoidAnnounceList(request.getEntityOidList());
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 批量还原公告<br/>
     * url:/api/admin/announcement/list/revert<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOidList
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_REVERT_LIST)
    public String doRevertAnnounceList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "公告id列表不能为空!");
            announcementBizService.doRevertAnnounceList(request.getEntityOidList());
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 批量删除公告<br/>
     * url:/api/admin/announcement/list/remove<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOidList
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(AnnounceApiUrlConfig.URL_ANNOUNCEMENT_REMOVE_LIST)
    public String doRemoveAnnounceList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "公告id列表不能为空!");
            announcementBizService.doRemoveAnnounceList(request.getEntityOidList());
        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

}
