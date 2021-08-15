package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.CommentApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.CommentAddRequest;
import cn.jianwoo.blog.dto.request.CommentPageRequest;
import cn.jianwoo.blog.dto.request.CommentReplyRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.CommentInfoResponse;
import cn.jianwoo.blog.dto.response.CommentListResponse;
import cn.jianwoo.blog.dto.response.CommentSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleCommentListVO;
import cn.jianwoo.blog.dto.response.vo.CommentSummaryVO;
import cn.jianwoo.blog.dto.response.vo.CommentVO;
import cn.jianwoo.blog.entity.query.CommentParam;
import cn.jianwoo.blog.enums.CommReadEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.bo.CommentBO;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:43
 */
@RestController
@RequestMapping(CommentApiUrlConfig.URL_PREFIX)
@Slf4j
public class CommentApiController extends BaseController {
    @Autowired
    private CommentBizService commentBizService;


    /**
     * 文章评论添加(文章编辑页面)<br/>
     * url:/api/admin/comment/add<br/>
     *
     * @param param JSON 参数({@link CommentAddRequest})
     *              commentText<br/>
     *              username<br/>
     *              qq<br/>
     *              artId<br/>
     *              commentParentId<br/>
     *              headImgUrl<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_ADD)
    public String doCreateComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentAddRequest request = this.convertParam(param, CommentAddRequest.class);
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
                    .with(CommentBO::setContactTel, request.getContactTel())//
                    .with(CommentBO::setContent, request.getCommentText())
                    .with(CommentBO::setParentOid, request.getCommentParentId())
                    .with(CommentBO::setHeadImgSrc, request.getHeadImgUrl())
                    .build();

            commentBizService.doAddComment(commentBO);

        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

    /**
     * 文章评论删除(文章编辑页面/评论列表)<br/>
     * url:/api/admin/comment/remove<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_REMOVE)
    public String doRemoveComm(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "评论id不能为空!");
            commentBizService.doDelCommentById(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 文章评论集合删除(评论列表)<br/>
     * url:/api/admin/comment/remove/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_REMOVE_LIST)
    public String doRemoveCommList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "评论id不能为空!");
            commentBizService.doDelCommentByListOid(request.getEntityOidList());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    //    @GetMapping(CommentApiUrlConfig.URL_COMMENT_QUERY)
    @Deprecated
    public String queryComment(Integer page, Integer limit, CommentParam param) {
        List<CommentBO> commentList = commentBizService.queryAllEffectiveComment(param);
        List<CommentSummaryVO> commentVOList = new ArrayList<>();
        CommentSummaryResponse response = new CommentSummaryResponse();
        Map<Long, Integer> oidSeqMap = new HashMap<>();
        Integer seq = 1;
        for (CommentBO commentBO : commentList) {
            CommentSummaryVO vo = new CommentSummaryVO();
            vo.setArtTitle(commentBO.getTitle());
            vo.setContent(commentBO.getContent());
            vo.setCommentTimeStr(DateUtil.formatDateTime(commentBO.getCommentTime()));
            vo.setCommentTime(commentBO.getCommentTime());
            vo.setUserName(commentBO.getUserName());
            vo.setReplyTo(commentBO.getParentUserName());
            vo.setReplyOid(commentBO.getParentOid());
            vo.setSeq(seq++);
            oidSeqMap.put(commentBO.getOid(), vo.getSeq());
            commentVOList.add(vo);
        }
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        List<CommentSummaryVO> resCommList = new ArrayList<>();
        Integer size = (page - 1) * limit + limit;
        if (size > commentVOList.size()) {
            size = commentVOList.size();
        }

        for (int i = (page - 1) * limit; i < size; i++) {
            CommentSummaryVO vo = commentVOList.get(i);
            Integer replySeq = oidSeqMap.get(vo.getReplyOid());
            if (vo.getReplyOid().equals(0L)) {
                vo.setReplyTo("文章");
            } else {
                vo.setReplyTo(vo.getReplyTo() + "(Seq: " + replySeq + ")");
            }
            resCommList.add(vo);
        }
        response.setData(resCommList);
        response.setCount(Long.parseLong(String.valueOf(commentVOList.size())));

        return super.responseToJSONString(response);
    }

    /**
     * 分页查询评论(评论列表)<br/>
     * url:/api/admin/comment/query<br/>
     *
     * @param param JSON 参数({@link CommentPageRequest})
     *              title<br/>
     *              unread<br/>
     * @return 返回响应 {@link CommentSummaryResponse}
     * code<br/>
     * count<br/>
     * data<br/>
     * --seq<br/>
     * --artTitle<br/>
     * --userName<br/>
     * --commentTimeDesc<br/>
     * --replyTo<br/>
     * --content<br/>
     * --replyOid<br/>
     * --oid<br/>
     * --artOid<br/>
     * --clientIp<br/>
     * --userArea<br/>
     * --desc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(CommentApiUrlConfig.URL_COMMENT_QUERY)
    public String queryCommentPage(CommentPageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));
        CommentParam commParam = new CommentParam();
        if (StringUtils.isNotBlank(param.getTitle())) {
            commParam.setTitle(param.getTitle().trim());
        }
        commParam.setPageNo(param.getPage());
        commParam.setPageSize(param.getLimit());
        if (null != param.getUnread() && new Integer(1).equals(param.getUnread())) {
            commParam.setReadStatus(new Integer(1).equals(param.getUnread()) ? CommReadEnum.UNREAD.getValue()
                    : CommReadEnum.READ.getValue());
        }
        PageInfo<CommentBO> commentExtPageInfo = commentBizService.queryAllCommentPage(commParam);
        List<CommentSummaryVO> commentVOList = new ArrayList<>();
        CommentSummaryResponse response = new CommentSummaryResponse();
        for (CommentBO commentBO : commentExtPageInfo.getList()) {
            CommentSummaryVO vo = new CommentSummaryVO();
            vo.setArtTitle(commentBO.getTitle());
            vo.setContent(StringEscapeUtils.escapeHtml4(commentBO.getContent()));
            vo.setCommentTimeStr(DateUtil.formatDateTime(commentBO.getCommentTime()));
            vo.setCommentTime(commentBO.getCommentTime());
            vo.setUserName(commentBO.getUserName());
            vo.setReplyTo(commentBO.getParentUserName());
            vo.setReplyOid(commentBO.getParentOid());
            vo.setOid(commentBO.getOid());
            vo.setArtOid(commentBO.getArticleOid());
            commentVOList.add(vo);
        }

        response.setData(commentVOList);
        response.setCount(commentExtPageInfo.getTotal());
        return super.responseToJSONString(response);

    }

    /**
     * 评论回复(评论列表)<br/>
     * url:/api/admin/comment/reply<br/>
     *
     * @param param JSON 参数({@link CommentReplyRequest})
     *              content<br/>
     *              parentOid<br/>
     *              artOid<br/>
     *              headImgUrl<br/>
     *              qq<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.COMMENT_REPLY)
    @SubToken
    @ApiVersion()
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_REPLY)
    public String doRreplyComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentReplyRequest request = this.convertParam(param, CommentReplyRequest.class);
            BizValidation.paramValidate(request.getContent(), "content", "回复内容不能为空!");
            BizValidation.paramValidate(request.getParentOid(), "parentOid", "评论父oid不能为空!");
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章oid不能为空!");
            CommentBO commentBO = JwBuilder.of(CommentBO::new)
                    .with(CommentBO::setArticleOid, request.getArtOid())
                    .with(CommentBO::setUserName, request.getUserName())
                    .with(CommentBO::setClientIp, request.getClientIp())
                    .with(CommentBO::setContactQq, request.getContactQq())
                    .with(CommentBO::setContent, request.getContent())
                    .with(CommentBO::setParentOid, request.getParentOid())
                    .with(CommentBO::setHeadImgSrc, request.getHeadImgUrl())
                    .build();
            commentBizService.doAddComment(commentBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 根据文章oid查询文章评论(文章编辑列表)<br/>
     * url:/api/admin/comment/query/article/list<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * commentList
     * --headImgSrc
     * --userName
     * --commentTimeStr
     * --commentTime
     * --content
     * --parentUserName
     * --oid
     * --replyList
     * ----headImgSrc
     * ----userName
     * ----commentTimeStr
     * ----commentTime
     * ----content
     * ----parentUserName
     * ----oid
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_QUERY_ARTICLE_LIST)
    @ApiVersion()
    public String queryCommentListByArticle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "文章主键不能为空!");
            List<CommentBO> commentExtList = commentBizService.queryCommentsByArtOid(request.getEntityOid());
            log.info(">>query comment Service by article oid[{}]: {}", request.getEntityOid(),
                    JSON.toJSONString(commentExtList));
            CommentListResponse response = CommentListResponse.getInstance();
            List<ArticleCommentListVO> commentListVOList = new ArrayList<>();
            for (CommentBO commentBO : commentExtList) {
                ArticleCommentListVO vo = new ArticleCommentListVO();
                vo.setUserName(commentBO.getUserName());
                vo.setContent(commentBO.getContent());
                vo.setCommentTimeStr(DateUtil.formatDateTime(commentBO.getCommentTime()));
                vo.setCommentTime(commentBO.getCommentTime());
                vo.setHeadImgSrc(commentBO.getHeadImgSrc());
                vo.setOid(commentBO.getOid());
                List<ArticleCommentListVO> replyList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(commentBO.getReplyComments())) {
                    for (CommentBO rep : commentBO.getReplyComments()) {
                        ArticleCommentListVO replyVo = new ArticleCommentListVO();
                        replyVo.setUserName(rep.getUserName());
                        replyVo.setContent(rep.getContent());
                        replyVo.setCommentTimeStr(DateUtil.formatDateTime(rep.getCommentTime()));
                        replyVo.setCommentTime(rep.getCommentTime());
                        replyVo.setHeadImgSrc(rep.getHeadImgSrc());
                        replyVo.setOid(rep.getOid());
                        replyVo.setParentUserName(rep.getParentUserName());
                        replyList.add(replyVo);
                    }
                    vo.setReplyList(replyList);
                }

                commentListVOList.add(vo);
            }
            response.setCommentList(commentListVOList);
            return super.responseToJSONString(response);

        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }

    }

    /**
     * 文章已读(評論列表列表)<br/>
     * url:/api/admin/comment/read/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_READ_LIST)
    public String doReadCommList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "评论id不能为空!");
            commentBizService.doUpdateReadByOidList(request.getEntityOidList());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 文章已读(評論列表列表)<br/>
     * url:/api/admin/comment/read<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_READ)
    public String doReadComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "评论id不能为空!");
            commentBizService.doUpdateReadByOid(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 文获取评论信息<br/>
     * url:/api/admin/comment/info/{id}<br/>
     *
     * @param id
     * @return 返回响应 {@link CommentInfoResponse}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * data
     * --oid
     * --userName
     * --headImgSrc
     * --contactQq
     * --contactWechat
     * --contactWeibo
     * --contactTel
     * --clientIp
     * --content
     * --commentTime
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(CommentApiUrlConfig.URL_COMMENT_INFO)
    public String queryCommentInfo(@PathVariable("id") Long id) {
        CommentInfoResponse response = CommentInfoResponse.getInstance();
        CommentBO commentBO = commentBizService.queryCommentExtByOid(id);
        CommentVO vo = JwBuilder.of(CommentVO::new)
                .with(CommentVO::setOid, commentBO.getOid())
                .with(CommentVO::setUserName, commentBO.getUserName())
                .with(CommentVO::setArtOid, commentBO.getArticleOid())
                .with(CommentVO::setArtTitle, commentBO.getTitle())
                .with(CommentVO::setHeadImgSrc, commentBO.getHeadImgSrc())
                .with(CommentVO::setClientIp, commentBO.getClientIp())
                .with(CommentVO::setUserArea, DomainUtil.format(commentBO.getUserArea(), Constants.UNKNOW))
                .with(CommentVO::setContactQq, commentBO.getContactQq())
                .with(CommentVO::setContactWeibo, commentBO.getContactWeibo())
                .with(CommentVO::setContactWechat, commentBO.getContactWechat())
                .with(CommentVO::setContactTel, commentBO.getContactTel())
                .with(CommentVO::setContent, commentBO.getContent())
                .with(CommentVO::setReadStatus, commentBO.getReadStatus())
                .with(CommentVO::setCommentTime, commentBO.getCommentTime())
                .with(CommentVO::setCommentTimeStr, DateUtil.formatDateTime(commentBO.getCommentTime())).build();
        response.setData(vo);

        return super.responseToJSONString(response);
    }
}
