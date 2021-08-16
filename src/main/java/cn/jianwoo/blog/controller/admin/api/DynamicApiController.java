package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.DynamicApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.CommentPageRequest;
import cn.jianwoo.blog.dto.request.VisitPageRequest;
import cn.jianwoo.blog.dto.response.CommentSummaryResponse;
import cn.jianwoo.blog.dto.response.VisitResponse;
import cn.jianwoo.blog.dto.response.vo.CommentSummaryVO;
import cn.jianwoo.blog.dto.response.vo.VisitVO;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.VisitBizService;
import cn.jianwoo.blog.service.bo.CommentBO;
import cn.jianwoo.blog.service.bo.VisitBO;
import cn.jianwoo.blog.service.param.CommentParam;
import cn.jianwoo.blog.service.param.VisitParam;
import cn.jianwoo.blog.util.DomainUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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
    private VisitBizService visitBizService;
    @Autowired
    private CommentBizService commentBizService;

    /**
     * 查詢最近的访问列表(动态首页)<br/>
     * url:/api/admin/dynamic/visit/query<br/>
     *
     * @param param JSON 参数({@link VisitPageRequest})
     * @return 返回响应 {@link VisitResponse}
     * code<br/>
     * count<br/>
     * data<br/>
     * --ip<br/>
     * --visitDate<br/>
     * --articleTitle<br/>
     * --articleOid<br/>
     * --area<br/>
     * --desc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(DynamicApiUrlConfig.URL_VISIT_QUERY)
    public String queryVisitList(VisitPageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));

        VisitParam pageParam = new VisitParam();
        pageParam.setPageNo(param.getPage());
        pageParam.setPageSize(param.getLimit());
        PageInfo<VisitBO> pageInfo = visitBizService.queryRecentVisitPageList(pageParam);
        VisitResponse response = VisitResponse.getInstance();
        if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<VisitVO> list = new ArrayList<>();
            pageInfo.getList().forEach(domain -> {
                VisitVO vo = new VisitVO();
                vo.setArticleOid(domain.getArticleOid());
                vo.setArticleTitle(domain.getArticleTitle());
                vo.setVisitIp(domain.getVisitIp());
                vo.setVisitArea(domain.getVisitArea());
                vo.setVisitDateStr(DateUtil.formatDateTime(domain.getVisitTime()));
                vo.setVisitTime(domain.getVisitTime());
                list.add(vo);
            });
            response.setData(list);
            response.setCount(pageInfo.getTotal());
        }
        return super.responseToJSONString(response);

    }


    /**
     * 查詢最近的文章评论列表(动态首页)<br/>
     * url:/api/admin/comment/visit/query<br/>
     *
     * @param param JSON 参数({@link VisitPageRequest})
     * @return 返回响应 {@link CommentSummaryResponse}
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
    @ApiVersion()
    @GetMapping(DynamicApiUrlConfig.URL_COMMENT_QUERY)
    public String queryCommPageList(CommentPageRequest param) {
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
                vo.setArtTitle(domain.getTitle());
                vo.setUserName(domain.getUserName());
                vo.setClientIp(domain.getClientIp());
//                vo.setArea(domain.getArea());
                String content = StringEscapeUtils.escapeHtml4(domain.getContent());
                if (content.length() > 50) {
                    content.substring(0, 50).concat(Constants.ELLIPSIS);
                }
                vo.setContent(content);
                list.add(vo);
            });
            response.setData(list);
            response.setCount(pageInfo.getTotal());
        }
        return super.responseToJSONString(response);

    }
}
