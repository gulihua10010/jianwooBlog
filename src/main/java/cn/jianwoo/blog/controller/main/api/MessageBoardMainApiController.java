package cn.jianwoo.blog.controller.main.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.MessageBoardMainApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dto.request.MessageBoardMainPageRequest;
import cn.jianwoo.blog.dto.request.MessageBoardPraiseRequest;
import cn.jianwoo.blog.dto.request.MessageBoardRemoveRequest;
import cn.jianwoo.blog.dto.request.MessageBoardRequest;
import cn.jianwoo.blog.dto.response.MessageBoardListResponse;
import cn.jianwoo.blog.dto.response.vo.MessageBoardListVO;
import cn.jianwoo.blog.dto.response.vo.UserInfoVO;
import cn.jianwoo.blog.exception.ValidationException;
import cn.jianwoo.blog.service.biz.MessagBoardBizService;
import cn.jianwoo.blog.service.bo.MessageBoardBO;
import cn.jianwoo.blog.service.bo.MessageBoardMainPageListBO;
import cn.jianwoo.blog.service.bo.UserInfoBO;
import cn.jianwoo.blog.service.param.MessageBoardMainParam;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-11 11:27
 */
@RestController
@RequestMapping(MessageBoardMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class MessageBoardMainApiController extends BaseController {

    @Autowired
    private MessagBoardBizService messagBoardBizService;


    /**
     * 查询留言版<br/>
     * url:/api/message/board/query/page/list<br/>
     *
     * @param param JSON 参数({@link MessageBoardMainPageRequest})<br/>
     *              parentOid<br/>
     *              replyRootOid<br/>
     *              orderWay<br/>
     * @return 返回响应 {@link MessageBoardListResponse}<br/>
     * count<br/>
     * rootMsgCount<br/>
     * userInfo<br/>
     * userInfo<br/>
     * --userId<br/>
     * --userName<br/>
     * --userNick<br/>
     * --userRegion<br/>
     * --avatarSrc<br/>
     * --contactQq<br/>
     * --contactWechat<br/>
     * --contactWeibo<br/>
     * --contactEmail<br/>
     * messageList<br/>
     * --avatarSrc<br/>
     * --userId<br/>
     * --userName<br/>
     * --userNick<br/>
     * --userRegion<br/>
     * --contactQq<br/>
     * --contactWechat<br/>
     * --contactWeibo<br/>
     * --contactEmail<br/>
     * --pushTimeStr<br/>
     * --pushTime<br/>
     * --content<br/>
     * --floorNumber<br/>
     * --isPraise<br/>
     * --flagEdit<br/>
     * --flagAdmin<br/>
     * --praiseCount<br/>
     * --oid<br/>
     * --replyCount<br/>
     * --replyList<br/>
     * ----avatarSrc<br/>
     * ----userId<br/>
     * ----userName<br/>
     * ----userNick<br/>
     * ----userRegion<br/>
     * ----contactQq<br/>
     * ----contactWechat<br/>
     * ----contactWeibo<br/>
     * ----contactEmail<br/>
     * ----pushTimeStr<br/>
     * ----pushTime<br/>
     * ----content<br/>
     * ----replyToUserId<br/>
     * ----replyToUserName<br/>
     * ----replyToUserNick<br/>
     * ----isPraise<br/>
     * ----flagEdit<br/>
     * ----flagAdmin<br/>
     * ----flagDelete<br/>
     * ----praiseCount<br/>
     * ----oid<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @PostMapping(MessageBoardMainApiUrlConfig.URL_MESSAGE_BOARD_QUERY_PAGE_LIST)
    @ApiVersion()
    @IpLimit(key = "queryMessageBoardPageList")
    public String queryMessageBoardPageList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MessageBoardMainPageRequest req = this.convertParam(param, MessageBoardMainPageRequest.class);
            if (null == req.getParentOid() && null == req.getReplyRootOid()) {
                throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL, "留言父ID和回复根ID不能全部为空!", "parentOid||replyRootOid");
            }

            MessageBoardMainParam messageParam = new MessageBoardMainParam();
            messageParam.setParentOid(req.getParentOid());
            messageParam.setReplyRootOid(req.getReplyRootOid());
            messageParam.setRefOid(req.getRefOid());
            messageParam.setOrderWay(req.getOrderWay());
            messageParam.setCurrentIp(JwUtil.getRealIpAddress(request));
            messageParam.setPageNo(req.getPage());
            messageParam.setPageSize(req.getLimit());
            MessageBoardMainPageListBO messageResBO = messagBoardBizService.queryMessagesMainPage(messageParam);

            MessageBoardListResponse response = MessageBoardListResponse.getInstance();
            List<MessageBoardListVO> messageListVOList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(messageResBO.getList())) {
                for (MessageBoardBO messageBO : messageResBO.getList()) {
                    MessageBoardListVO vo = new MessageBoardListVO();
                    vo.setUserId(messageBO.getUserId());
                    vo.setUserName(messageBO.getUserName());
                    vo.setUserNick(messageBO.getUserNick());
                    vo.setUserRegion(messageBO.getUserRegion());
                    vo.setContactWeibo(messageBO.getContactWeibo());
                    vo.setContactWechat(messageBO.getContactWechat());
                    vo.setContactQq(messageBO.getContactQq());
                    vo.setContactEmail(messageBO.getContactEmail());
                    vo.setContent(messageBO.getContent());
                    vo.setPushTimeStr(DateUtil.formatDateTime(messageBO.getPushTime()));
                    vo.setPushTime(messageBO.getPushTime());
                    vo.setAvatarSrc(messageBO.getAvatarSrc());
                    vo.setOid(messageBO.getOid());
                    vo.setIsPraise(messageBO.getIsPraise());
                    vo.setPraiseCount(messageBO.getPraiseCount());
                    vo.setFlagEdit(messageBO.getFlagEdit());
                    vo.setFlagAdmin(messageBO.getFlagAdmin());
                    vo.setFlagDelete(messageBO.getIsDelete());
                    vo.setReplyCount(messageBO.getReplyCount());
                    List<MessageBoardListVO> replyList = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(messageBO.getReplyMessages())) {
                        for (MessageBoardBO rep : messageBO.getReplyMessages()) {
                            MessageBoardListVO replyVo = new MessageBoardListVO();
                            replyVo.setUserId(rep.getUserId());
                            replyVo.setUserName(rep.getUserName());
                            replyVo.setUserNick(rep.getUserNick());
                            replyVo.setUserRegion(rep.getUserRegion());
                            replyVo.setContactWeibo(rep.getContactWeibo());
                            replyVo.setContactWechat(rep.getContactWechat());
                            replyVo.setContactQq(rep.getContactQq());
                            replyVo.setContactEmail(rep.getContactEmail());
                            replyVo.setContent(rep.getContent());
                            replyVo.setPushTimeStr(DateUtil.formatDateTime(rep.getPushTime()));
                            replyVo.setPushTime(rep.getPushTime());
                            replyVo.setAvatarSrc(rep.getAvatarSrc());
                            replyVo.setOid(rep.getOid());
                            replyVo.setReplyToUserId(rep.getReplyToUserId());
                            replyVo.setReplyToUserName(rep.getReplyToUserName());
                            replyVo.setReplyToUserNick(rep.getReplyToUserNick());
                            replyVo.setIsPraise(rep.getIsPraise());
                            replyVo.setPraiseCount(rep.getPraiseCount());
                            replyVo.setReplyCount(rep.getReplyCount());
                            replyVo.setFlagEdit(rep.getFlagEdit());
                            replyVo.setFlagAdmin(rep.getFlagAdmin());
                            replyVo.setFlagDelete(rep.getIsDelete());
                            replyList.add(replyVo);
                        }
                        vo.setReplyList(replyList);
                    }

                    messageListVOList.add(vo);
                }
            }
            if (null != messageResBO.getUserInfo()) {
                UserInfoBO userInfoBO = messageResBO.getUserInfo();
                UserInfoVO userInfoVO = new UserInfoVO();
                userInfoVO.setAvatarSrc(userInfoBO.getAvatarSrc());
                userInfoVO.setUserId(userInfoBO.getUserId());
                userInfoVO.setUserName(userInfoBO.getUserName());
                userInfoVO.setUserNick(userInfoBO.getUserNick());
                userInfoVO.setUserRegion(userInfoBO.getUserRegion());
                userInfoVO.setContactEmail(userInfoBO.getContactEmail());
                userInfoVO.setContactQq(userInfoBO.getContactQq());
                userInfoVO.setContactWechat(userInfoBO.getContactWechat());
                userInfoVO.setContactWeibo(userInfoBO.getContactWeibo());
                response.setUserInfo(userInfoVO);
            }

            response.setMessageList(messageListVOList);
            response.setCount(messageResBO.getCount());
            response.setRootMsgCount(messageResBO.getRootMsgCount());
            return super.responseToJSONString(response);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 留言创建<br/>
     * url:/api/message/board/create<br/>
     *
     * @param param JSON 参数({@link MessageBoardRequest})<br/>
     *              content<br/>
     *              userNick<br/>
     *              contactQq<br/>
     *              contactEmail<br/>
     *              msgParentId<br/>
     *              avatarSrc<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @IpLimit(key = "doCreateMessage")
    @PostMapping(MessageBoardMainApiUrlConfig.URL_MESSAGE_BOARD_CREATE)
    public String doCreateMessage(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MessageBoardRequest req = this.convertParam(param, MessageBoardRequest.class);
            BizValidation.paramValidate(req.getMsgParentId(), "msgParentId", "留言父id不能为空!");
            BizValidation.paramValidate(req.getContent(), "content", "留言内容不能为空!");
            BizValidation.paramValidate(req.getUserNick(), "userNick", "用户昵称不能为空!");
            BizValidation.paramValidate(req.getContactEmail(), "contactEmail", "用户邮箱不能为空!");
            BizValidation.paramLengthValidate(req.getUserNick(), Constants.NICK_LENGTH, "userNick", "昵称不能大于20个字符!");
            BizValidation.paramLengthValidate(req.getContactEmail(), Constants.EMAIL_LENGTH, "contactEmail", "用户邮箱不能大于30个字符!");
            BizValidation.paramMinLengthValidate(req.getContent(), Constants.MESSAGE_BOARD_MIN_CONTENT_LENGTH, "content", "留言内容最少为5个字符!");
            BizValidation.paramLengthValidate(req.getContent(), Constants.MESSAGE_BOARD_MAX_CONTENT_LENGTH, "content", "留言内容不能超过200个字符!");
            BizValidation.paramRegexValidate(req.getUserNick(), Constants.NICK_REGEX, "userNick", "昵称只能为[中文数字英文和$_] !");
            BizValidation.paramRegexValidate(req.getContactEmail(), Constants.EMAIL_REGEX, "contactEmail", "邮箱格式不正确!");
            MessageBoardBO messageBO = JwBuilder.of(MessageBoardBO::new)
                    .with(MessageBoardBO::setUserNick, req.getUserNick())
                    .with(MessageBoardBO::setClientIp, JwUtil.getRealIpAddress(request))
                    .with(MessageBoardBO::setContactQq, req.getContactQq())
                    .with(MessageBoardBO::setContactWechat, req.getContactWechat())
                    .with(MessageBoardBO::setContactWeibo, req.getContactWeibo())
                    .with(MessageBoardBO::setContactEmail, req.getContactEmail())
                    .with(MessageBoardBO::setContent, req.getContent())
                    .with(MessageBoardBO::setParentOid, req.getMsgParentId())
                    .with(MessageBoardBO::setAvatarSrc, req.getAvatarSrc())
                    .build();

            messagBoardBizService.doCreateMessageBoard(messageBO, false);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 留言更新<br/>
     * url:/api/message/board/update<br/>
     *
     * @param param JSON 参数({@link MessageBoardRequest})<br/>
     *              oid<br/>
     *              content<br/>
     *              userNick<br/>
     *              qq<br/>
     *              contactEmail<br/>
     *              msgParentId<br/>
     *              avatarSrc<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MessageBoardMainApiUrlConfig.URL_MESSAGE_BOARD_UPDATE)
    @IpLimit(key = "doUpdateMessage")
    public String doUpdateMessage(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MessageBoardRequest req = this.convertParam(param, MessageBoardRequest.class);
            BizValidation.paramValidate(req.getOid(), "oid", "留言oid不能为空!");
            BizValidation.paramValidate(req.getContent(), "content", "留言内容不能为空!");
            BizValidation.paramValidate(req.getUserNick(), "userNick", "用户昵称不能为空!");
            BizValidation.paramValidate(req.getContactEmail(), "contactEmail", "用户邮箱不能为空!");
            BizValidation.paramLengthValidate(req.getUserNick(), Constants.NICK_LENGTH, "userNick", "昵称不能大于20个字符!");
            BizValidation.paramLengthValidate(req.getContactEmail(), Constants.EMAIL_LENGTH, "contactEmail", "用户邮箱不能大于30个字符!");
            BizValidation.paramMinLengthValidate(req.getContent(), Constants.MESSAGE_BOARD_MIN_CONTENT_LENGTH, "content", "留言内容最少为5个字符!");
            BizValidation.paramLengthValidate(req.getContent(), Constants.MESSAGE_BOARD_MAX_CONTENT_LENGTH, "content", "留言内容不能超过200个字符!");
            BizValidation.paramRegexValidate(req.getUserNick(), Constants.NICK_REGEX, "userNick", "昵称只能为中文数字英文和$_!");
            BizValidation.paramRegexValidate(req.getContactEmail(), Constants.EMAIL_REGEX, "contactEmail", "邮箱格式不正确!");
            MessageBoardBO messageBO = JwBuilder.of(MessageBoardBO::new)
                    .with(MessageBoardBO::setOid, req.getOid())
                    .with(MessageBoardBO::setUserName, req.getUserNick())
                    .with(MessageBoardBO::setClientIp, JwUtil.getRealIpAddress(request))
                    .with(MessageBoardBO::setContactQq, req.getContactQq())
                    .with(MessageBoardBO::setContactWechat, req.getContactWechat())
                    .with(MessageBoardBO::setContactWeibo, req.getContactWeibo())
                    .with(MessageBoardBO::setContactEmail, req.getContactEmail())
                    .with(MessageBoardBO::setContent, req.getContent())
                    .with(MessageBoardBO::setAvatarSrc, req.getAvatarSrc())
                    .build();

            messagBoardBizService.doUpdateMessage(messageBO);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 留言赞<br/>
     * url:/api/message/board/praise/add<br/>
     *
     * @param param JSON 参数({@link MessageBoardPraiseRequest})<br/>
     *              msgOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MessageBoardMainApiUrlConfig.URL_MESSAGE_BOARD_PRAISE_ADD)
    @IpLimit(key = "doAddMessagePraise")
    public String doAddMessagePraise(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MessageBoardPraiseRequest req = this.convertParam(param, MessageBoardPraiseRequest.class);
            BizValidation.paramValidate(req.getMsgOid(), "msgOid", "留言id不能为空!");

            messagBoardBizService.doAddPraise(req.getMsgOid(), JwUtil.getRealIpAddress(request));

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 文章留言删除(文章详情页面)<br/>
     * url:/api/message/board/remove<br/>
     *
     * @param param JSON 参数({@link MessageBoardRemoveRequest})<br/>
     *              msgOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MessageBoardMainApiUrlConfig.URL_MESSAGE_BOARD_REMOVE)
    @IpLimit(key = "doRemoveMessage")
    public String doRemoveMessage(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MessageBoardRemoveRequest req = this.convertParam(param, MessageBoardRemoveRequest.class);
            BizValidation.paramValidate(req.getMsgOid(), "msgOid", "留言id不能为空!");

            messagBoardBizService.doDelMessageById(Long.parseLong(req.getMsgOid()), JwUtil.getRealIpAddress(request), false);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }
}
