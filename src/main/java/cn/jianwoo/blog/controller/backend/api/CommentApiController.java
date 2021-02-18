package cn.jianwoo.blog.controller.backend.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.router.CommentApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.CommentAddRequest;
import cn.jianwoo.blog.dto.request.CommentPageRequest;
import cn.jianwoo.blog.dto.request.CommentReplyRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.CommentListResponse;
import cn.jianwoo.blog.dto.response.CommentResponse;
import cn.jianwoo.blog.dto.response.LayuiBaseResponse;
import cn.jianwoo.blog.dto.response.vo.CommentListVO;
import cn.jianwoo.blog.dto.response.vo.CommentReplyListVO;
import cn.jianwoo.blog.dto.response.vo.CommentVO;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.extension.ReplyCommentsExt;
import cn.jianwoo.blog.entity.query.CommentParam;
import cn.jianwoo.blog.enums.CommReadEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_ADD)
    public String addComment(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentAddRequest request = this.convertParam(param, CommentAddRequest.class);
            BizValidation.paramValidate(request.getArtId(), "artId", "文章id不能为空!");
            BizValidation.paramValidate(request.getCommentParentId(), "commentParentId", "评论父id不能为空!");
            BizValidation.paramValidate(request.getCommentText(), "commentText", "评论内容不能为空!");
            BizValidation.paramValidate(request.getUsername(), "username", "用户不能为空!");
            commentBizService.doAddComment(request.getArtId(), request.getUsername(), request.getClientIp(),
                    request.getCommentText(), request.getCommentParentId(), request.getQq(), request.getHeadImgUrl());

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
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_REMOVE)
    public String removeComm(@RequestBody String param) {
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
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_REMOVE_LIST)
    public String removeCommList(@RequestBody String param) {
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
    public String queryComm(Integer page, Integer limit, CommentParam param) {
        List<CommentExt> commentList = commentBizService.queryAllEffectiveComment(param);
        List<CommentVO> commentVOList = new ArrayList<>();
        CommentResponse response = new CommentResponse();
        Map<Long, Integer> oidSeqMap = new HashMap<>();
        Integer seq = 1;
        for (CommentExt commentExt : commentList) {
            CommentVO vo = new CommentVO();
            vo.setArtTitle(commentExt.getTitle());
            vo.setContent(commentExt.getContent());
            vo.setDate(DateUtil.formatDateTime(commentExt.getDate()));
            vo.setUser(commentExt.getUser());
            vo.setReplyTo(commentExt.getParentUserName());
            vo.setReplyOid(commentExt.getParent());
            vo.setSeq(seq++);
            oidSeqMap.put(commentExt.getOid(), vo.getSeq());
            commentVOList.add(vo);
        }
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        List<CommentVO> resCommList = new ArrayList<>();
        Integer size = (page - 1) * limit + limit;
        if (size > commentVOList.size()) {
            size = commentVOList.size();
        }

        for (int i = (page - 1) * limit; i < size; i++) {
            CommentVO vo = commentVOList.get(i);
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
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);

        return super.responseToJSONString(response);
    }

    /**
     * 分页查询评论(评论列表)<br/>
     * url:/api/admin/comment/query<br/>
     *
     * @param param JSON 参数({@link CommentPageRequest})
     *              title<br/>
     *              unread<br/>
     * @return 返回响应 {@link CommentResponse}
     * code<br/>
     * count<br/>
     * data<br/>
     * --seq<br/>
     * --artTitle<br/>
     * --user<br/>
     * --date<br/>
     * --replyTo<br/>
     * --content<br/>
     * --replyOid<br/>
     * --oid<br/>
     * --artOid<br/>
     * --ip<br/>
     * --area<br/>
     * --desc<br/>
     * @author gulihua
     */
    @GetMapping(CommentApiUrlConfig.URL_COMMENT_QUERY)
    public String queryCommPage(CommentPageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));
        CommentParam commParam = new CommentParam();
        if (StringUtils.isNotBlank(param.getTitle())) {
            commParam.setTitle(param.getTitle().trim());
        }
        commParam.setPageNo(param.getPage());
        commParam.setPageSize(param.getLimit());
        if (null != param.getUnread() && new Integer(1).equals(param.getUnread())) {
            commParam.setIsRead(new Integer(1).equals(param.getUnread()) ? CommReadEnum.UNREAD.getValue()
                    : CommReadEnum.READ.getValue());
        }
        PageInfo<CommentExt> commentExtPageInfo = commentBizService.queryAllCommentPage(commParam);
        List<CommentVO> commentVOList = new ArrayList<>();
        CommentResponse response = new CommentResponse();
        for (CommentExt commentExt : commentExtPageInfo.getList()) {
            CommentVO vo = new CommentVO();
            vo.setArtTitle(commentExt.getTitle());
            vo.setContent(StringEscapeUtils.escapeHtml4(commentExt.getContent()));
            vo.setDate(DateUtil.formatDateTime(commentExt.getDate()));
            vo.setUser(commentExt.getUser());
            vo.setReplyTo(commentExt.getParentUserName());
            vo.setReplyOid(commentExt.getParent());
            vo.setOid(commentExt.getOid());
            vo.setArtOid(commentExt.getArticleOid());
            commentVOList.add(vo);
        }

        response.setData(commentVOList);
        response.setCount(commentExtPageInfo.getTotal());
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
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
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_REPLY)
    public String replyComm(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            CommentReplyRequest request = this.convertParam(param, CommentReplyRequest.class);
            BizValidation.paramValidate(request.getContent(), "content", "回复内容不能为空!");
            BizValidation.paramValidate(request.getParentOid(), "parentOid", "评论父oid不能为空!");
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章oid不能为空!");
            commentBizService.doAddComment(request.getArtOid(), Constants.BLOG_AUTHOR, request.getClientIp(),
                    request.getContent(), request.getParentOid(), request.getQq(), request.getHeadImgUrl());
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
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_QUERY_ARTICLE_LIST)
    public String queryArticleList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "文章主键不能为空!");
            List<CommentExt> commentExtList = commentBizService.queryCommentsByArtOid(request.getEntityOid());
            CommentListResponse response = CommentListResponse.getInstance();
            List<CommentListVO> commentListVOList = new ArrayList<>();
            for (CommentExt ext : commentExtList) {
                CommentListVO vo = new CommentListVO();
                vo.setUser(ext.getUser());
                vo.setContent(ext.getContent());
                vo.setDate(DateUtil.formatDateTime(ext.getDate()));
                vo.setHeadImg(ext.getHeadImg());
                vo.setOid(ext.getOid());
                List<CommentReplyListVO> replyList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(ext.getReplyComments())) {
                    for (ReplyCommentsExt rep : ext.getReplyComments()) {
                        CommentReplyListVO replyVo = new CommentReplyListVO();
                        replyVo.setUser(rep.getUser());
                        replyVo.setContent(rep.getContent());
                        replyVo.setDate(DateUtil.formatDateTime(rep.getDate()));
                        replyVo.setHeadImg(rep.getHeadImg());
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
     * url:/api/admin/comment/read<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(CommentApiUrlConfig.URL_COMMENT_READ)
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
}
