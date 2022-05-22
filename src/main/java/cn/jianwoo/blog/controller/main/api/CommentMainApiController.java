package cn.jianwoo.blog.controller.main.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.CommentMainApiUrlConfig;
import cn.jianwoo.blog.dto.request.ArticlePageRequest;
import cn.jianwoo.blog.dto.request.ArticlePraiseRequest;
import cn.jianwoo.blog.dto.request.CommentRequest;
import cn.jianwoo.blog.dto.request.CommentPageRequest;
import cn.jianwoo.blog.dto.request.CommentPraiseRequest;
import cn.jianwoo.blog.dto.request.CommentRemoveRequest;
import cn.jianwoo.blog.dto.response.CommentListResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleCommentListVO;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.bo.CommentBO;
import cn.jianwoo.blog.service.param.PageParam;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
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
     * url:/api/comment/query/article/list<br/>
     *
     * @param param JSON 参数({@link CommentPageRequest})<br/>
     *              artOid<br/>
     * @return 返回响应 {@link CommentListResponse}<br/>
     * commentList<br/>
     * --avatarSrc<br/>
     * --userName<br/>
     * --commentTimeStr<br/>
     * --commentTime<br/>
     * --content<br/>
     * --parentUserName<br/>
     * --oid<br/>
     * --replyList<br/>
     * ----avatarSrc<br/>
     * ----userName<br/>
     * ----commentTimeStr<br/>
     * ----commentTime<br/>
     * ----content<br/>
     * ----parentUserName<br/>
     * ----oid<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @PostMapping(CommentMainApiUrlConfig.URL_QUERY_PAGE_LIST)
    @ApiVersion()
    @IpLimit(key = "queryCommentListByArticle")
    public String queryCommentListByArticle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentPageRequest request = this.convertParam(param, CommentPageRequest.class);
            PageParam pageParam = new PageParam();
            pageParam.setPageNo(request.getPage());
            pageParam.setPageSize(request.getLimit());
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章ID不能为空!");
            PageInfo<CommentBO> commentExtList = commentBizService.queryCommentsMainPageByArtOid(request.getArtOid(), pageParam);

            CommentListResponse response = CommentListResponse.getInstance();
            List<ArticleCommentListVO> commentListVOList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(commentExtList.getList())) {
                for (CommentBO commentBO : commentExtList.getList()) {
                    ArticleCommentListVO vo = new ArticleCommentListVO();
                    vo.setUserName(commentBO.getUserName());
                    vo.setContent(commentBO.getContent());
                    vo.setCommentTimeStr(DateUtil.formatDateTime(commentBO.getCommentTime()));
                    vo.setCommentTime(commentBO.getCommentTime());
                    vo.setAvatarSrc(commentBO.getAvatarSrc());
                    vo.setOid(commentBO.getOid());
                    List<ArticleCommentListVO> replyList = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(commentBO.getReplyComments())) {
                        for (CommentBO rep : commentBO.getReplyComments()) {
                            ArticleCommentListVO replyVo = new ArticleCommentListVO();
                            replyVo.setUserName(rep.getUserName());
                            replyVo.setContent(rep.getContent());
                            replyVo.setCommentTimeStr(DateUtil.formatDateTime(rep.getCommentTime()));
                            replyVo.setCommentTime(rep.getCommentTime());
                            replyVo.setAvatarSrc(rep.getAvatarSrc());
                            replyVo.setOid(rep.getOid());
                            replyVo.setParentUserName(rep.getParentUserName());
                            replyList.add(replyVo);
                        }
                        vo.setReplyList(replyList);
                    }

                    commentListVOList.add(vo);
                }
            }

            response.setCommentList(commentListVOList);
            return super.responseToJSONString(response);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 文章评论添加(文章详情页面)<br/>
     * url:/api/comment/create<br/>
     *
     * @param param JSON 参数({@link CommentRequest})
     *              commentText<br/>
     *              username<br/>
     *              qq<br/>
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
    @PostMapping(CommentMainApiUrlConfig.URL_CREATE_COMMENT)
    public String doCreateComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentRequest request = this.convertParam(param, CommentRequest.class);
            BizValidation.paramValidate(request.getArtId(), "artId", "文章id不能为空!");
            BizValidation.paramValidate(request.getCommentParentId(), "commentParentId", "评论父id不能为空!");
            BizValidation.paramValidate(request.getCommentText(), "commentText", "评论内容不能为空!");
            BizValidation.paramValidate(request.getUsername(), "username", "用户不能为空!");
            CommentBO commentBO = JwBuilder.of(CommentBO::new)
                    .with(CommentBO::setArticleOid, request.getArtId())
                    .with(CommentBO::setUserName, request.getUsername())
                    .with(CommentBO::setClientIp, request.getClientIp())
                    .with(CommentBO::setContactQq, request.getContactQq())
                    .with(CommentBO::setContactWechat, request.getContactWechat())
                    .with(CommentBO::setContactWeibo, request.getContactWeibo())
                    .with(CommentBO::setContactTel, request.getContactTel())
                    .with(CommentBO::setContent, request.getCommentText())
                    .with(CommentBO::setParentOid, request.getCommentParentId())
                    .with(CommentBO::setAvatarSrc, request.getAvatarSrc())
                    .build();

            commentBizService.doCreateComment(commentBO);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }



    /**
     * 文章评论更新(文章详情页面)<br/>
     * url:/api/comment/create<br/>
     *
     * @param param JSON 参数({@link CommentRequest})
     *              oid<br/>
     *              commentText<br/>
     *              username<br/>
     *              qq<br/>
     *              artId<br/>
     *              commentParentId<br/>
     *              avatarSrc<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentMainApiUrlConfig.URL_UPDATE_COMMENT)
    @IpLimit(key = "doUpdateComment")
    public String doUpdateComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentRequest request = this.convertParam(param, CommentRequest.class);
            BizValidation.paramValidate(request.getOid(), "oid", "评论oid不能为空!");
            BizValidation.paramValidate(request.getArtId(), "artId", "文章id不能为空!");
            BizValidation.paramValidate(request.getCommentParentId(), "commentParentId", "评论父id不能为空!");
            BizValidation.paramValidate(request.getCommentText(), "commentText", "评论内容不能为空!");
            BizValidation.paramValidate(request.getUsername(), "username", "用户不能为空!");
            CommentBO commentBO = JwBuilder.of(CommentBO::new)
                    .with(CommentBO::setOid, request.getOid())
                    .with(CommentBO::setArticleOid, request.getArtId())
                    .with(CommentBO::setUserName, request.getUsername())
                    .with(CommentBO::setClientIp, request.getClientIp())
                    .with(CommentBO::setContactQq, request.getContactQq())
                    .with(CommentBO::setContactWechat, request.getContactWechat())
                    .with(CommentBO::setContactWeibo, request.getContactWeibo())
                    .with(CommentBO::setContactTel, request.getContactTel())
                    .with(CommentBO::setContent, request.getCommentText())
                    .with(CommentBO::setParentOid, request.getCommentParentId())
                    .with(CommentBO::setAvatarSrc, request.getAvatarSrc())
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
     * @param param JSON 参数({@link ArticlePraiseRequest})
     *              commOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentMainApiUrlConfig.URL_COMMENT_PRAISE_ADD)
    @IpLimit(key = "doAddPraise2")
    public String doAddPraise(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentPraiseRequest req = this.convertParam(param, CommentPraiseRequest.class);
            BizValidation.paramValidate(req.getCommOid(), "commOid", "评论id不能为空!");

            commentBizService.doAddPraise(req.getCommOid(), request.getRemoteAddr());

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 文章评论删除(文章详情页面)<br/>
     * url:/api/comment/remove<br/>
     *
     * @param param JSON 参数({@link ArticlePraiseRequest})
     *              commOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentMainApiUrlConfig.URL_REMOVE_COMMENT)
    @IpLimit(key = "doRemoveComment")
    public String doRemoveComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentRemoveRequest req = this.convertParam(param, CommentRemoveRequest.class);
            BizValidation.paramValidate(req.getCommOid(), "commOid", "评论id不能为空!");

            commentBizService.doDelCommentById(Long.parseLong(req.getCommOid()), request.getRemoteAddr());

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }
}
