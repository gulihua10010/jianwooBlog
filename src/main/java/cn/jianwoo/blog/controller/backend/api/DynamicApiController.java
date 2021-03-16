package cn.jianwoo.blog.controller.backend.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.router.DynamicApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.CommentPageRequest;
import cn.jianwoo.blog.dto.request.VisitPageRequest;
import cn.jianwoo.blog.dto.response.CommentSummaryResponse;
import cn.jianwoo.blog.dto.response.VisitResponse;
import cn.jianwoo.blog.dto.response.vo.CommentVO;
import cn.jianwoo.blog.dto.response.vo.VisitVO;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.extension.VisitExt;
import cn.jianwoo.blog.entity.query.CommentParam;
import cn.jianwoo.blog.entity.query.VisitParam;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.VisitBizService;
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
    @GetMapping(DynamicApiUrlConfig.URL_VISIT_QUERY)
    public String queryVisitList(VisitPageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));

        VisitParam pageParam = new VisitParam();
        pageParam.setPageNo(param.getPage());
        pageParam.setPageSize(param.getLimit());
        PageInfo<VisitExt> pageInfo = visitBizService.queryRecentVisitPageList(pageParam);
        VisitResponse response = VisitResponse.getInstance();
        if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<VisitVO> list = new ArrayList<>();
            pageInfo.getList().forEach(domain -> {
                VisitVO vo = new VisitVO();
                vo.setArticleOid(domain.getArticleOid());
                vo.setArticleTitle(domain.getTitle());
                vo.setIp(domain.getIp());
                vo.setArea(domain.getArea());
                vo.setVisitDate(DateUtil.formatDateTime(domain.getVisitDate()));
                list.add(vo);
            });
            response.setData(list);
            response.setCount(pageInfo.getTotal());
        }
        return super.responseToJSONString(response);

    }


    /**
     * 查詢最近的文章评论列表(动态首页)<br/>
     * url:/api/admin/dynamic/visit/query<br/>
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
    @GetMapping(DynamicApiUrlConfig.URL_COMMENT_QUERY)
    public String queryCommPageList(CommentPageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));

        CommentParam pageParam = new CommentParam();
        pageParam.setPageNo(param.getPage());
        pageParam.setPageSize(param.getLimit());
        PageInfo<CommentExt> pageInfo = commentBizService.queryAllCommentPage(pageParam);
        CommentSummaryResponse response = CommentSummaryResponse.getInstance();
        if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<CommentVO> list = new ArrayList<>();
            pageInfo.getList().forEach(domain -> {
                CommentVO vo = new CommentVO();
                vo.setArtOid(domain.getArticleOid());
                vo.setDate(DateUtil.formatDateTime(domain.getDate()));
                vo.setArtTitle(domain.getTitle());
                vo.setUser(domain.getUser());
                vo.setIp(domain.getIp());
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
