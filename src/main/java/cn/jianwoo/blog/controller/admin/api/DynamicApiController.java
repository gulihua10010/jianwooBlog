package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.DynamicApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.CommentPageRequest;
import cn.jianwoo.blog.dto.request.AccessPageRequest;
import cn.jianwoo.blog.dto.request.MessageBoardPageRequest;
import cn.jianwoo.blog.dto.response.CommentSummaryResponse;
import cn.jianwoo.blog.dto.response.AccessResponse;
import cn.jianwoo.blog.dto.response.MessageBoardSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.CommentSummaryVO;
import cn.jianwoo.blog.dto.response.vo.AccessVO;
import cn.jianwoo.blog.dto.response.vo.MessageBoardSummaryVO;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.ArticleAccessBizService;
import cn.jianwoo.blog.service.biz.MessagBoardBizService;
import cn.jianwoo.blog.service.bo.CommentBO;
import cn.jianwoo.blog.service.bo.AccessBO;
import cn.jianwoo.blog.service.bo.MessageBoardBO;
import cn.jianwoo.blog.service.param.CommentParam;
import cn.jianwoo.blog.service.param.AccessParam;
import cn.jianwoo.blog.service.param.MessageBoardParam;
import cn.jianwoo.blog.util.DomainUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 17:38
 */
@RestController
@RequestMapping(DynamicApiUrlConfig.URL_PREFIX)
@Slf4j
public class DynamicApiController extends BaseController {
    @Autowired
    private ArticleAccessBizService articleAccessBizService;
    @Autowired
    private CommentBizService commentBizService;
    @Autowired
    private MessagBoardBizService messagBoardBizService;

    /**
     * 查詢最近的访问列表(动态首页)<br/>
     * url:/api/admin/dynamic/access/query<br/>
     *
     * @param param JSON 参数({@link AccessPageRequest})<br/>
     * @return 返回响应 {@link AccessResponse}<br/>
     * code<br/>
     * count<br/>
     * data<br/>
     * --accessIp<br/>
     * --accessDate<br/>
     * --articleTitle<br/>
     * --articleOid<br/>
     * --accessRegion<br/>
     * --desc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(DynamicApiUrlConfig.URL_ACCESS_QUERY)
    public String queryAccessList(AccessPageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));

            AccessParam pageParam = new AccessParam();
            pageParam.setPageNo(param.getPage());
            pageParam.setPageSize(param.getLimit());
            PageInfo<AccessBO> pageInfo = articleAccessBizService.queryRecentAccessPageList(pageParam);
            AccessResponse response = AccessResponse.getInstance();
            if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
                List<AccessVO> list = new ArrayList<>();
                pageInfo.getList().forEach(domain -> {
                    AccessVO vo = new AccessVO();
                    vo.setArticleOid(domain.getArticleOid());
                    vo.setArticleTitle(domain.getArticleTitle());
                    vo.setAccessIp(domain.getAccessIp());
                    vo.setAccessRegion(domain.getAccessRegion());
                    vo.setAccessDateStr(DateUtil.formatDateTime(domain.getAccessTime()));
                    vo.setAccessTime(domain.getAccessTime());
                    list.add(vo);
                });
                response.setData(list);
                response.setCount(pageInfo.getTotal());
            }
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 查詢最近的文章评论列表(动态首页)<br/>
     * url:/api/admin/dynamic/comment/query<br/>
     *
     * @param param JSON 参数({@link CommentPageRequest})<br/>
     * @return 返回响应 {@link CommentSummaryResponse}<br/>
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
     * --userRegion<br/>
     * --desc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(DynamicApiUrlConfig.URL_COMMENT_QUERY)
    public String queryCommPageList(CommentPageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));

            CommentParam pageParam = new CommentParam();
            pageParam.setPageNo(param.getPage());
            pageParam.setPageSize(param.getLimit());
            PageInfo<CommentBO> pageInfo = commentBizService.queryAllCommentPage(pageParam);
            CommentSummaryResponse response = CommentSummaryResponse.getInstance();
            if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
                List<CommentSummaryVO> list = new ArrayList<>();
                pageInfo.getList().forEach(domain -> {
                    CommentSummaryVO vo = new CommentSummaryVO();
                    vo.setArtOid(domain.getArticleOid());
                    vo.setCommentTimeStr(DateUtil.formatDateTime(domain.getCommentTime()));
                    vo.setCommentTime(domain.getCommentTime());
                    vo.setArtTitle(domain.getArticleTitle());
                    vo.setUserNick(domain.getUserNick());
                    vo.setClientIp(domain.getClientIp());
                    vo.setUserRegion(domain.getUserRegion());
                    String content = StringEscapeUtils.escapeHtml4(domain.getContent());//
                    if (content.length() > 50) {
                        content = content.substring(0, 50).concat(Constants.ELLIPSIS);
                    }
                    vo.setContent(content);
                    list.add(vo);
                });
                response.setData(list);
                response.setCount(pageInfo.getTotal());
            }
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 查詢最近的文章留言列表(动态首页)<br/>
     * url:/api/admin/dynamic/message/board/query<br/>
     *
     * @param param JSON 参数({@link MessageBoardPageRequest})<br/>
     * @return 返回响应 {@link MessageBoardSummaryResponse}<br/>
     * code<br/>
     * count<br/>
     * data<br/>
     * --seq<br/>
     * --user<br/>
     * --date<br/>
     * --replyTo<br/>
     * --content<br/>
     * --replyOid<br/>
     * --oid<br/>\
     * --ip<br/>
     * --userRegion<br/>
     * --desc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(DynamicApiUrlConfig.URL_MESSAGE_BOARD_QUERY)
    public String queryMsgBoardPageList(MessageBoardPageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));

            MessageBoardParam pageParam = new MessageBoardParam();
            pageParam.setPageNo(param.getPage());
            pageParam.setPageSize(param.getLimit());
            PageInfo<MessageBoardBO> pageInfo = messagBoardBizService.queryAllMessagePage(pageParam);
            MessageBoardSummaryResponse response = MessageBoardSummaryResponse.getInstance();
            if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
                List<MessageBoardSummaryVO> list = new ArrayList<>();
                pageInfo.getList().forEach(domain -> {
                    MessageBoardSummaryVO vo = new MessageBoardSummaryVO();
                    vo.setPushTimeStr(DateUtil.formatDateTime(domain.getPushTime()));
                    vo.setPushTime(domain.getPushTime());
                    vo.setUserNick(domain.getUserNick());
                    vo.setClientIp(domain.getClientIp());
                    vo.setUserRegion(domain.getUserRegion());
                    String content = StringEscapeUtils.escapeHtml4(domain.getContent());//
                    if (content.length() > 50) {
                        content = content.substring(0, 50).concat(Constants.ELLIPSIS);
                    }
                    vo.setContent(content);
                    list.add(vo);
                });
                response.setData(list);
                response.setCount(pageInfo.getTotal());
            }
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }
}
