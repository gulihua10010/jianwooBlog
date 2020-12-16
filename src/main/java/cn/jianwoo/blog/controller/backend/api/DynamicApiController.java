package cn.jianwoo.blog.controller.backend.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.page.DynamicApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.VisitPageRequest;
import cn.jianwoo.blog.dto.response.CommentResponse;
import cn.jianwoo.blog.dto.response.LayuiBaseResponse;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DynamicApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(DynamicApiController.class);
    @Autowired
    private VisitBizService visitBizService;
    @Autowired
    private CommentBizService commentBizService;

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
                vo.setVisitDate(DateUtil.format(domain.getVisitDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
                list.add(vo);
            });
            response.setData(list);
            response.setCount(pageInfo.getTotal());
        }
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
        return super.responseToJSONString(response);

    }


    @GetMapping(DynamicApiUrlConfig.URL_COMMENT_QUERY)
    public String queryCommPageList(VisitPageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));

        CommentParam pageParam = new CommentParam();
        pageParam.setPageNo(param.getPage());
        pageParam.setPageSize(param.getLimit());
        PageInfo<CommentExt> pageInfo = commentBizService.queryAllCommentPage(pageParam);
        CommentResponse response = CommentResponse.getInstance();
        if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<CommentVO> list = new ArrayList<>();
            pageInfo.getList().forEach(domain -> {
                CommentVO vo = new CommentVO();
                vo.setArtOid(domain.getArticleOid());
                vo.setDate(DateUtil.format(domain.getDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
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
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
        return super.responseToJSONString(response);

    }
}
