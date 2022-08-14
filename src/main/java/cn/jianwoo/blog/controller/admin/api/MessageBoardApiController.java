package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.MessageBoardApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.MessageBoardPageRequest;
import cn.jianwoo.blog.dto.request.MessageBoardReplyRequest;
import cn.jianwoo.blog.dto.response.MessageBoardInfoResponse;
import cn.jianwoo.blog.dto.response.MessageBoardSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.MessageBoardSummaryVO;
import cn.jianwoo.blog.dto.response.vo.MessageBoardVO;
import cn.jianwoo.blog.enums.CommReadEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.MessagBoardBizService;
import cn.jianwoo.blog.service.bo.MessageBoardBO;
import cn.jianwoo.blog.service.param.MessageBoardParam;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
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
 * @date 2020-09-14 19:43
 */
@RestController
@RequestMapping(MessageBoardApiUrlConfig.URL_PREFIX)
@Slf4j
public class MessageBoardApiController extends BaseController {
    @Autowired
    private MessagBoardBizService messagBoardBizService;


    /**
     * 留言删除(留言列表)<br/>
     * url:/api/admin/message/board/remove<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MessageBoardApiUrlConfig.URL_MESSAGE_REMOVE)
    public String doRemoveMessage(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest req = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(req.getEntityOid(), "entityOid", "留言id不能为空!");
            messagBoardBizService.doDelMessageById(req.getEntityOid(), JwUtil.getRealIpAddress(request), true);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 留言集合删除(留言列表)<br/>
     * url:/api/admin/message/board/remove/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})<br/>
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MessageBoardApiUrlConfig.URL_MESSAGE_REMOVE_LIST)
    public String doRemoveMessageList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest req = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(req.getEntityOidList(), "entityOidList", "留言id不能为空!");
            messagBoardBizService.doDelMessageByListOid(req.getEntityOidList(), JwUtil.getRealIpAddress(request));
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 分页查询留言(留言列表)<br/>
     * url:/api/admin/message/board/query/list<br/>
     *
     * @param param JSON 参数({@link MessageBoardPageRequest})<br/>
     *              unread<br/>
     *              oid<br/>
     * @return 返回响应 {@link MessageBoardSummaryResponse}<br/>
     * code<br/>
     * count<br/>
     * data<br/>
     * --seq<br/>
     * --userNick<br/>
     * --pushTimeDesc<br/>
     * --replyTo<br/>
     * --content<br/>
     * --replyOid<br/>
     * --oid<br/>
     * --artOid<br/>
     * --clientIp<br/>
     * --userRegion<br/>
     * --desc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(MessageBoardApiUrlConfig.URL_MESSAGE_QUERY_LIST)
    public String queryMessagePageList(MessageBoardPageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));
            MessageBoardParam msgParam = new MessageBoardParam();
            msgParam.setPageNo(param.getPage());
            msgParam.setPageSize(param.getLimit());
            msgParam.processSortField(param.getSortField(), param.getSortOrder());


            if (null != param.getUnread() && new Integer(1).equals(param.getUnread())) {
                msgParam.setReadStatus(new Integer(1).equals(param.getUnread()) ? CommReadEnum.UNREAD.getValue()
                        : CommReadEnum.READ.getValue());
            }
            msgParam.setOid(param.getOid());

            PageInfo<MessageBoardBO> pageInfo = messagBoardBizService.queryAllMessagePage(msgParam);
            List<MessageBoardSummaryVO> list = new ArrayList<>();
            MessageBoardSummaryResponse response = MessageBoardSummaryResponse.getInstance();
            for (MessageBoardBO messageBO : pageInfo.getList()) {
                MessageBoardSummaryVO vo = new MessageBoardSummaryVO();
                String content = StringEscapeUtils.escapeHtml4(messageBO.getContent());
                if (content.length() > 50) {
                    content.substring(0, 50).concat(Constants.ELLIPSIS);
                }
                vo.setContent(content);
                vo.setPushTime(messageBO.getPushTime());
                vo.setPushTimeStr(DateUtil.formatDateTime(messageBO.getPushTime()));
                vo.setUserNick(messageBO.getUserNick());
                vo.setReplyTo(messageBO.getReplyToUserNick());
                vo.setReplyOid(messageBO.getParentOid());
                vo.setOid(messageBO.getOid());
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
     * 留言回复(留言列表)<br/>
     * url:/api/admin/message/board/reply<br/>
     *
     * @param param JSON 参数({@link MessageBoardReplyRequest})<br/>
     *              userNick<br/>
     *              content<br/>
     *              parentOid<br/>
     *              avatarSrc<br/>
     *              qq<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @PageId(PageIdEnum.MESSAGE_BOARD_REPLY)
    @SubToken
    @ApiVersion()
    @PostMapping(MessageBoardApiUrlConfig.URL_MESSAGE_REPLY)
    public String doReplyMessage(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MessageBoardReplyRequest request = this.convertParam(param, MessageBoardReplyRequest.class);
            BizValidation.paramValidate(request.getUserNick(), "userNick", "用户昵称不能为空!");
            BizValidation.paramValidate(request.getContent(), "content", "回复内容不能为空!");
            BizValidation.paramValidate(request.getParentOid(), "parentOid", "留言父oid不能为空!");
            MessageBoardBO messageBO = JwBuilder.of(MessageBoardBO::new)
                    .with(MessageBoardBO::setUserNick, request.getUserNick())
                    .with(MessageBoardBO::setClientIp, request.getClientIp())
                    .with(MessageBoardBO::setContactQq, request.getContactQq())
                    .with(MessageBoardBO::setContent, request.getContent())
                    .with(MessageBoardBO::setParentOid, request.getParentOid())
                    .with(MessageBoardBO::setAvatarSrc, request.getAvatarSrc())
                    .build();
            messagBoardBizService.doCreateMessageBoard(messageBO, true);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    /**
     * 留言批量已读<br/>
     * url:/api/admin/message/board/read/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})<br/>
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MessageBoardApiUrlConfig.URL_MESSAGE_READ_LIST)
    public String doReadMessageList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "留言id不能为空!");
            messagBoardBizService.doUpdateReadByOidList(request.getEntityOidList());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 留言已读<br/>
     * url:/api/admin/message/board/read<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MessageBoardApiUrlConfig.URL_MESSAGE_READ)
    public String doReadMessage(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "留言id不能为空!");
            messagBoardBizService.doUpdateReadByOid(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 文获取留言信息<br/>
     * url:/api/admin/message/board/info/{id}<br/>
     *
     * @param id
     * @return 返回响应 {@link MessageBoardInfoResponse}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * data<br/>
     * --oid<br/>
     * --userId<br/>
     * --userName<br/>
     * --userNick<br/>
     * --avatarSrc<br/>
     * --contactQq<br/>
     * --contactWechat<br/>
     * --contactWeibo<br/>
     * --contactTel<br/>
     * --clientIp<br/>
     * --content<br/>
     * --pushTime<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(MessageBoardApiUrlConfig.URL_MESSAGE_INFO)
    public String queryMessageInfo(@PathVariable("id") String id) {
        MessageBoardInfoResponse response = null;
        try {
            BizValidation.paramValidate(id, "id", "留言id不能为空!");
            response = MessageBoardInfoResponse.getInstance();
            MessageBoardBO messageBO = messagBoardBizService.queryMessageExtByOid(id);
            MessageBoardVO vo = JwBuilder.of(MessageBoardVO::new)
                    .with(MessageBoardVO::setOid, messageBO.getOid())
                    .with(MessageBoardVO::setUserId, messageBO.getUserId())
                    .with(MessageBoardVO::setUserName, messageBO.getUserName())
                    .with(MessageBoardVO::setUserNick, messageBO.getUserNick())
                    .with(MessageBoardVO::setAvatarSrc, messageBO.getAvatarSrc())
                    .with(MessageBoardVO::setClientIp, messageBO.getClientIp())
                    .with(MessageBoardVO::setUserRegion, DomainUtil.format(messageBO.getUserRegion(), Constants.UNKNOW))
                    .with(MessageBoardVO::setContactQq, messageBO.getContactQq())
                    .with(MessageBoardVO::setContactWeibo, messageBO.getContactWeibo())
                    .with(MessageBoardVO::setContactWechat, messageBO.getContactWechat())
                    .with(MessageBoardVO::setContactTel, messageBO.getContactTel())
                    .with(MessageBoardVO::setContent, messageBO.getContent())
                    .with(MessageBoardVO::setReadStatus, messageBO.getReadStatus())
                    .with(MessageBoardVO::setPushTime, messageBO.getPushTime())
                    .with(MessageBoardVO::setPushTimeStr, DateUtil.formatDateTime(messageBO.getPushTime())).build();
            response.setData(vo);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(response);
    }
}
