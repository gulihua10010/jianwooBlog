package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BasePageRequestDto;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.MsgApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.MsgPageRequest;
import cn.jianwoo.blog.dto.request.MsgTimerRequest;
import cn.jianwoo.blog.dto.response.MsgSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.MsgSummaryVO;
import cn.jianwoo.blog.service.biz.MsgBizService;
import cn.jianwoo.blog.service.bo.MsgProfileBO;
import cn.jianwoo.blog.service.param.MsgParam;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
 * @date 2021-07-18 0:10
 */
@RestController
@RequestMapping(MsgApiUrlConfig.URL_PREFIX)
@Slf4j
public class MsgApiController extends BaseController {
    @Autowired
    private MsgBizService msgBizService;


    /**
     * 分页查询消息(消息列表)<br/>
     * url:/api/admin/msg/query/list<br/>
     *
     * @param param JSON 参数({@link BasePageRequestDto})<br/>
     * @return 返回响应 {@link MsgSummaryResponse}<br/>
     * status<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --content<br/>
     * --link<br/>
     * --isRead<br/>
     * --sendTimeDesc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(MsgApiUrlConfig.URL_MSG_QUERY_LIST)
    public String queryMsgPage(MsgPageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));
            MsgParam pageParam = new MsgParam();
            pageParam.setPageNo(param.getPage());
            pageParam.setPageSize(param.getLimit());
            if (StringUtils.isNotBlank(param.getIsRead())) {
                pageParam.setIsRead(param.getIsRead());
            }
            PageInfo<MsgProfileBO> pageInfo = msgBizService.queryMsgPageList(pageParam);

            MsgSummaryResponse response = MsgSummaryResponse.getInstance();
            List<MsgSummaryVO> list = new ArrayList<>();
            for (MsgProfileBO mgProfileBO : pageInfo.getList()) {
                MsgSummaryVO vo = JwBuilder.of(MsgSummaryVO::new)
                        .with(MsgSummaryVO::setOid, mgProfileBO.getOid())
                        .with(MsgSummaryVO::setTitle, mgProfileBO.getMsgTitle())
                        .with(MsgSummaryVO::setContent, mgProfileBO.getMsgContent())
                        .with(MsgSummaryVO::setLink, mgProfileBO.getMsgLink())
                        .with(MsgSummaryVO::setSendTime, mgProfileBO.getSendTime())
                        .with(MsgSummaryVO::setIsRead, mgProfileBO.getFlagRead() ? Constants.TRUE_1 : Constants.FALSE_0)
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
     * 标记消息为已读<br/>
     * url:/api/admin/msg/read<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              oid<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MsgApiUrlConfig.URL_MSG_READ)
    public String doReadMsg(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "消息id不能为空!");

            msgBizService.doReadMsg(request.getEntityOid());
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 标记全部消息为已读<br/>
     * url:/api/admin/msg/all/read<br/>
     *
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MsgApiUrlConfig.URL_ALL_MSG_READ)
    public String doReadAllMsg(@RequestBody String param) {
        try {
            super.printRequestParams(param);

            msgBizService.doReadAllMsg();
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 页面定时获取最新消息(消息列表)<br/>
     * url:/api/admin/msg/timer/newest/query/list<br/>
     *
     * @param param JSON 参数({@link BasePageRequestDto})<br/>
     * @return 返回响应 {@link MsgSummaryResponse}<br/>
     * status<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --content<br/>
     * --link<br/>
     * --isRead<br/>
     * --sendTimeDesc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MsgApiUrlConfig.URL_MSG_TIMER_NEWEST_QUERY_LIST)
    public String queryMsgTimerList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MsgTimerRequest request = this.convertParam(param, MsgTimerRequest.class);
            List<MsgProfileBO> resList = msgBizService.queryMsgTimerList(request.getLimit());

            MsgSummaryResponse response = MsgSummaryResponse.getInstance();
            List<MsgSummaryVO> list = new ArrayList<>();
            for (MsgProfileBO mgProfileBO : resList) {
                MsgSummaryVO vo = JwBuilder.of(MsgSummaryVO::new)
                        .with(MsgSummaryVO::setOid, mgProfileBO.getOid())
                        .with(MsgSummaryVO::setTitle, mgProfileBO.getMsgTitle())
                        .with(MsgSummaryVO::setContent, mgProfileBO.getMsgContent())
                        .with(MsgSummaryVO::setLink, mgProfileBO.getMsgLink())
                        .with(MsgSummaryVO::setSendTime, mgProfileBO.getSendTime())
                        .with(MsgSummaryVO::setIsRead, mgProfileBO.getFlagRead() ? Constants.TRUE_1 : Constants.FALSE_0)
                        .build();
                list.add(vo);
            }
            response.setData(list);

            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 获取未读消息数量(消息列表)<br/>
     * url:/api/admin/msg/unread/count<br/>
     *
     * @return 返回响应 {@link MsgSummaryResponse}<br/>
     * status<br/>
     * count<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(MsgApiUrlConfig.URL_MSG_UNREAD_COUNT)
    public String queryUnreadMsgCount() {
        try {
            Long count = msgBizService.queryUnreadMsgCount();

            MsgSummaryResponse response = MsgSummaryResponse.getInstance();

            response.setCount(count);

            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }

}
