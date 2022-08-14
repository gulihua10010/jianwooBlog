package cn.jianwoo.blog.controller.main.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.CommentMainApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dto.request.CommentMainPageRequest;
import cn.jianwoo.blog.dto.request.CommentPraiseRequest;
import cn.jianwoo.blog.dto.request.CommentRemoveRequest;
import cn.jianwoo.blog.dto.request.CommentRequest;
import cn.jianwoo.blog.dto.response.CommentListResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleCommentListVO;
import cn.jianwoo.blog.dto.response.vo.UserInfoVO;
import cn.jianwoo.blog.exception.ValidationException;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.bo.CommentBO;
import cn.jianwoo.blog.service.bo.CommentMainPageListBO;
import cn.jianwoo.blog.service.bo.UserInfoBO;
import cn.jianwoo.blog.service.param.CommentMainParam;
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
@RequestMapping(CommentMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class CommentMainApiController extends BaseController {

    @Autowired
    private CommentBizService commentBizService;


    /**
     * 根据文章oid查询文章评论(文章详情页面)<br/>
     * url:/api/comment/article/query/page/list<br/>
     *
     * @param param JSON 参数({@link CommentMainPageRequest})<br/>
     *              artOid<br/>
     *              parentOid<br/>
     *              replyRootOid<br/>
     *              orderWay<br/>
     *              refOid<br/>
     * @return 返回响应 {@link CommentListResponse}<br/>
     * count<br/>
     * rootCommCount<br/>
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
     * commentList<br/>
     * --avatarSrc<br/>
     * --userId<br/>
     * --userName<br/>
     * --userNick<br/>
     * --userRegion<br/>
     * --contactQq<br/>
     * --contactWechat<br/>
     * --contactWeibo<br/>
     * --contactEmail<br/>
     * --commentTimeStr<br/>
     * --commentTime<br/>
     * --content<br/>
     * --floorNumber<br/>
     * --isPraise<br/>
     * --flagEdit<br/>
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
     * ----commentTimeStr<br/>
     * ----commentTime<br/>
     * ----content<br/>
     * ----replyToUserId<br/>
     * ----replyToUserName<br/>
     * ----replyToUserNick<br/>
     * ----isPraise<br/>
     * ----flagEdit<br/>
     * ----flagDelete<br/>
     * ----praiseCount<br/>
     * ----oid<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @PostMapping(CommentMainApiUrlConfig.URL_COMMENT_ARTICLE_QUERY_PAGE_LIST)
    @ApiVersion()
    @IpLimit(key = "queryCommentListByArticle")
    public String queryCommentListByArticle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentMainPageRequest req = this.convertParam(param, CommentMainPageRequest.class);
            BizValidation.paramValidate(req.getArtOid(), "artOid", "文章ID不能为空!");
            if (null == req.getParentOid() && null == req.getReplyRootOid()) {
                throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL, "评论父ID和回复根ID不能全部为空!", "parentOid||replyRootOid");
            }

            CommentMainParam commentParam = new CommentMainParam();
            commentParam.setArtOid(Long.valueOf(req.getArtOid()));
            commentParam.setParentOid(req.getParentOid());
            commentParam.setReplyRootOid(req.getReplyRootOid());
            commentParam.setRefOid(req.getRefOid());
            commentParam.setOrderWay(req.getOrderWay());
            commentParam.setCurrentIp(JwUtil.getRealIpAddress(request));
            commentParam.setPageNo(req.getPage());
            commentParam.setPageSize(req.getLimit());
            CommentMainPageListBO commentResBO = commentBizService.queryCommentsMainPageByArtOid(commentParam);

            CommentListResponse response = CommentListResponse.getInstance();
            List<ArticleCommentListVO> commentListVOList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(commentResBO.getList())) {
                for (CommentBO commentBO : commentResBO.getList()) {
                    ArticleCommentListVO vo = new ArticleCommentListVO();
                    vo.setUserId(commentBO.getUserId());
                    vo.setUserName(commentBO.getUserName());
                    vo.setUserNick(commentBO.getUserNick());
                    vo.setUserRegion(commentBO.getUserRegion());
                    vo.setContactWeibo(commentBO.getContactWeibo());
                    vo.setContactWechat(commentBO.getContactWechat());
                    vo.setContactQq(commentBO.getContactQq());
                    vo.setContactEmail(commentBO.getContactEmail());
                    vo.setContent(commentBO.getContent());
                    vo.setCommentTimeStr(DateUtil.formatDateTime(commentBO.getCommentTime()));
                    vo.setCommentTime(commentBO.getCommentTime());
                    vo.setAvatarSrc(commentBO.getAvatarSrc());
                    vo.setOid(commentBO.getOid());
                    vo.setFloorNumber(commentBO.getFloorNumber());
                    vo.setIsPraise(commentBO.getIsPraise());
                    vo.setPraiseCount(commentBO.getPraiseCount());
                    vo.setFlagEdit(commentBO.getFlagEdit());
                    vo.setFlagDelete(commentBO.getIsDelete());
                    vo.setReplyCount(commentBO.getReplyCount());
                    List<ArticleCommentListVO> replyList = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(commentBO.getReplyComments())) {
                        for (CommentBO rep : commentBO.getReplyComments()) {
                            ArticleCommentListVO replyVo = new ArticleCommentListVO();
                            replyVo.setUserId(rep.getUserId());
                            replyVo.setUserName(rep.getUserName());
                            replyVo.setUserNick(rep.getUserNick());
                            replyVo.setUserRegion(rep.getUserRegion());
                            replyVo.setContactWeibo(rep.getContactWeibo());
                            replyVo.setContactWechat(rep.getContactWechat());
                            replyVo.setContactQq(rep.getContactQq());
                            replyVo.setContactEmail(rep.getContactEmail());
                            replyVo.setContent(rep.getContent());
                            replyVo.setCommentTimeStr(DateUtil.formatDateTime(rep.getCommentTime()));
                            replyVo.setCommentTime(rep.getCommentTime());
                            replyVo.setAvatarSrc(rep.getAvatarSrc());
                            replyVo.setOid(rep.getOid());
                            replyVo.setReplyToUserId(rep.getReplyToUserId());
                            replyVo.setReplyToUserName(rep.getReplyToUserName());
                            replyVo.setReplyToUserNick(rep.getReplyToUserNick());
                            replyVo.setIsPraise(rep.getIsPraise());
                            replyVo.setPraiseCount(rep.getPraiseCount());
                            replyVo.setReplyCount(rep.getReplyCount());
                            replyVo.setFlagEdit(rep.getFlagEdit());
                            replyVo.setFlagDelete(rep.getIsDelete());
                            replyList.add(replyVo);
                        }
                        vo.setReplyList(replyList);
                    }

                    commentListVOList.add(vo);
                }
            }
            if (null != commentResBO.getUserInfo()) {
                UserInfoBO userInfoBO = commentResBO.getUserInfo();
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

            response.setCommentList(commentListVOList);
            response.setCount(commentResBO.getCount());
            response.setRootCommCount(commentResBO.getRootCommCount());
            return super.responseToJSONString(response);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 文章评论添加(文章详情页面)<br/>
     * url:/api/comment/create<br/>
     *
     * @param param JSON 参数({@link CommentRequest})<br/>
     *              commentText<br/>
     *              userNick<br/>
     *              contactQq<br/>
     *              contactEmail<br/>
     *              artId<br/>
     *              commentParentId<br/>
     *              avatarSrc<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @IpLimit(key = "doCreateComment")
    @PostMapping(CommentMainApiUrlConfig.URL_COMMENT_CREATE)
    public String doCreateComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentRequest req = this.convertParam(param, CommentRequest.class);
            BizValidation.paramValidate(req.getArtId(), "artId", "文章id不能为空!");
            BizValidation.paramValidate(req.getCommentParentId(), "commentParentId", "评论父id不能为空!");
            BizValidation.paramValidate(req.getCommentText(), "commentText", "评论内容不能为空!");
            BizValidation.paramValidate(req.getUserNick(), "userNick", "用户昵称不能为空!");
            BizValidation.paramValidate(req.getContactEmail(), "contactEmail", "用户邮箱不能为空!");
            BizValidation.paramLengthValidate(req.getUserNick(), Constants.NICK_LENGTH, "userNick", "昵称不能大于20个字符!");
            BizValidation.paramLengthValidate(req.getContactEmail(), Constants.EMAIL_LENGTH, "contactEmail", "用户邮箱不能大于30个字符!");
            BizValidation.paramMinLengthValidate(req.getCommentText(), Constants.COMMENT_MIN_CONTENT_LENGTH, "commentText", "评论内容最少为5个字符!");
            BizValidation.paramLengthValidate(req.getCommentText(), Constants.COMMENT_MAX_CONTENT_LENGTH, "commentText", "评论内容不能超过200个字符!");
            BizValidation.paramRegexValidate(req.getUserNick(), Constants.NICK_REGEX, "userNick", "昵称只能为[中文数字英文和$_] !");
            BizValidation.paramRegexValidate(req.getContactEmail(), Constants.EMAIL_REGEX, "contactEmail", "邮箱格式不正确!");
            CommentBO commentBO = JwBuilder.of(CommentBO::new)
                    .with(CommentBO::setArticleOid, req.getArtId())
                    .with(CommentBO::setUserNick, req.getUserNick())
                    .with(CommentBO::setClientIp, JwUtil.getRealIpAddress(request))
                    .with(CommentBO::setContactQq, req.getContactQq())
                    .with(CommentBO::setContactWechat, req.getContactWechat())
                    .with(CommentBO::setContactWeibo, req.getContactWeibo())
                    .with(CommentBO::setContactEmail, req.getContactEmail())
                    .with(CommentBO::setContent, req.getCommentText())
                    .with(CommentBO::setParentOid, req.getCommentParentId())
                    .with(CommentBO::setAvatarSrc, req.getAvatarSrc())
                    .build();

            commentBizService.doCreateComment(commentBO, false);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 文章评论更新(文章详情页面)<br/>
     * url:/api/comment/update<br/>
     *
     * @param param JSON 参数({@link CommentRequest})<br/>
     *              oid<br/>
     *              commentText<br/>
     *              userNick<br/>
     *              qq<br/>
     *              contactEmail<br/>
     *              artId<br/>
     *              commentParentId<br/>
     *              avatarSrc<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentMainApiUrlConfig.URL_COMMENT_UPDATE)
    @IpLimit(key = "doUpdateComment")
    public String doUpdateComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentRequest req = this.convertParam(param, CommentRequest.class);
            BizValidation.paramValidate(req.getOid(), "oid", "评论oid不能为空!");
            BizValidation.paramValidate(req.getArtId(), "artId", "文章id不能为空!");
            BizValidation.paramValidate(req.getCommentText(), "commentText", "评论内容不能为空!");
            BizValidation.paramValidate(req.getUserNick(), "userNick", "用户昵称不能为空!");
            BizValidation.paramValidate(req.getContactEmail(), "contactEmail", "用户邮箱不能为空!");
            BizValidation.paramLengthValidate(req.getUserNick(), Constants.NICK_LENGTH, "userNick", "昵称不能大于20个字符!");
            BizValidation.paramLengthValidate(req.getContactEmail(), Constants.EMAIL_LENGTH, "contactEmail", "用户邮箱不能大于30个字符!");
            BizValidation.paramMinLengthValidate(req.getCommentText(), Constants.COMMENT_MIN_CONTENT_LENGTH, "commentText", "评论内容最少为5个字符!");
            BizValidation.paramLengthValidate(req.getCommentText(), Constants.COMMENT_MAX_CONTENT_LENGTH, "commentText", "评论内容不能超过200个字符!");
            BizValidation.paramRegexValidate(req.getUserNick(), Constants.NICK_REGEX, "userNick", "昵称只能为中文数字英文和$_!");
            BizValidation.paramRegexValidate(req.getContactEmail(), Constants.EMAIL_REGEX, "contactEmail", "邮箱格式不正确!");
            CommentBO commentBO = JwBuilder.of(CommentBO::new)
                    .with(CommentBO::setOid, req.getOid())
                    .with(CommentBO::setArticleOid, req.getArtId())
                    .with(CommentBO::setUserName, req.getUserNick())
                    .with(CommentBO::setClientIp, JwUtil.getRealIpAddress(request))
                    .with(CommentBO::setContactQq, req.getContactQq())
                    .with(CommentBO::setContactWechat, req.getContactWechat())
                    .with(CommentBO::setContactWeibo, req.getContactWeibo())
                    .with(CommentBO::setContactEmail, req.getContactEmail())
                    .with(CommentBO::setContent, req.getCommentText())
                    .with(CommentBO::setAvatarSrc, req.getAvatarSrc())
                    .build();

            commentBizService.doUpdateComment(commentBO);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 文章评论赞(文章详情页面)<br/>
     * url:/api/comment/praise/add<br/>
     *
     * @param param JSON 参数({@link CommentPraiseRequest})<br/>
     *              commOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentMainApiUrlConfig.URL_COMMENT_PRAISE_ADD)
    @IpLimit(key = "doAddCommentPraise")
    public String doAddCommentPraise(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentPraiseRequest req = this.convertParam(param, CommentPraiseRequest.class);
            BizValidation.paramValidate(req.getCommOid(), "commOid", "评论id不能为空!");

            commentBizService.doAddPraise(req.getCommOid(), JwUtil.getRealIpAddress(request));

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 文章评论删除(文章详情页面)<br/>
     * url:/api/comment/remove<br/>
     *
     * @param param JSON 参数({@link CommentRemoveRequest})<br/>
     *              commOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentMainApiUrlConfig.URL_COMMENT_REMOVE)
    @IpLimit(key = "doRemoveComment")
    public String doRemoveComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentRemoveRequest req = this.convertParam(param, CommentRemoveRequest.class);
            BizValidation.paramValidate(req.getCommOid(), "commOid", "评论id不能为空!");

            commentBizService.doDelCommentById(Long.parseLong(req.getCommOid()), JwUtil.getRealIpAddress(request), false);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }
}
