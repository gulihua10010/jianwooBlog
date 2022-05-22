package cn.jianwoo.blog.controller.main.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.AnnounceMainApiUrlConfig;
import cn.jianwoo.blog.dto.request.AnnouncePageRequest;
import cn.jianwoo.blog.dto.response.AnnounceSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.AnnounceSummaryVO;
import cn.jianwoo.blog.service.biz.AnnouncementBizService;
import cn.jianwoo.blog.service.bo.AnnounceBO;
import cn.jianwoo.blog.service.param.AnnounceParam;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2022-04-07 14:32
 */
@RestController
@RequestMapping(AnnounceMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class AnnounceMainApiController extends BaseController {
    @Autowired
    private AnnouncementBizService announcementBizService;


    /**
     * 查询可用公告(首页)<br/>
     * url:/api/announce/query/useful/page/list<br/>
     *
     * @param param JSON 参数({@link AnnouncePageRequest})
     * @return 返回响应 {@link AnnounceSummaryResponse}
     * status<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --pushBy<br/>
     * --pushTime<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @PostMapping(AnnounceMainApiUrlConfig.URL_QUERY_USEFUL_PAGE_LIST)
    @ApiVersion()
    @IpLimit(key = "queryAnnounceList")
    public String queryAnnounceList(@RequestBody String param) {

        try {
            super.printRequestParams(param);
            AnnouncePageRequest request = this.convertParam(param, AnnouncePageRequest.class);
            AnnounceParam announceParam = new AnnounceParam();
            announceParam.setPageNo(request.getPage());
            announceParam.setPageSize(request.getLimit());
            PageInfo<AnnounceBO> pageInfo = announcementBizService.queryUsefulAnnouncePage(announceParam);

            AnnounceSummaryResponse response = AnnounceSummaryResponse.getInstance();
            List<AnnounceSummaryVO> list = new ArrayList<>();
            for (AnnounceBO emailTplBO : pageInfo.getList()) {
                AnnounceSummaryVO vo = JwBuilder.of(AnnounceSummaryVO::new)
                        .with(AnnounceSummaryVO::setOid, emailTplBO.getOid())
                        .with(AnnounceSummaryVO::setTitle, emailTplBO.getTitle())
                        .with(AnnounceSummaryVO::setExpiationTime, DateUtil.formatDateTime(emailTplBO.getExpiationTime()))
                        .with(AnnounceSummaryVO::setCreateTime, DateUtil.formatDateTime(emailTplBO.getCreateTime()))
                        .with(AnnounceSummaryVO::setPushTimeStr, DateUtil.formatDateTime(emailTplBO.getPushTime()))
                        .with(AnnounceSummaryVO::setPushTime, emailTplBO.getPushTime())
                        .with(AnnounceSummaryVO::setPushBy, emailTplBO.getPushBy())
                        .build();
                list.add(vo);
            }
            response.setData(list);

            response.setCount(pageInfo.getTotal());
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }
}
