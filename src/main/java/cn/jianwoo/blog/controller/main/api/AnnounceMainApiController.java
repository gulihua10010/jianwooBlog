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
     * 查询可用公告, 最多4条(首页)<br/>
     * url:/api/announce/query/useful/list<br/>
     *
     * @param param JSON 参数({@link AnnouncePageRequest})
     * @return 返回响应 {@link AnnounceSummaryResponse}
     * status<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --pushBy<br/>
     * --pushTime<br/>
     * --content<br/>
     * --createTime<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)<br/>
     * msg<br/>
     * @author gulihua
     */
    @PostMapping(AnnounceMainApiUrlConfig.URL_ANNOUNCE_QUERY_USEFUL_LIST)
    @ApiVersion()
    @IpLimit(key = "queryAnnounceList")
    public String queryAnnounceList(@RequestBody String param) {

        try {
            super.printRequestParams(param);
            List<AnnounceBO> announceList = announcementBizService.queryUsefulAnnounce();

            AnnounceSummaryResponse response = AnnounceSummaryResponse.getInstance();
            List<AnnounceSummaryVO> list = new ArrayList<>();
            for (AnnounceBO announceBO : announceList) {
                AnnounceSummaryVO vo = JwBuilder.of(AnnounceSummaryVO::new)
                        .with(AnnounceSummaryVO::setOid, announceBO.getOid())
                        .with(AnnounceSummaryVO::setTitle, announceBO.getTitle())
                        .with(AnnounceSummaryVO::setExpiationTime, DateUtil.formatDateTime(announceBO.getExpiationTime()))
                        .with(AnnounceSummaryVO::setCreateTime, DateUtil.formatDateTime(announceBO.getCreateTime()))
                        .with(AnnounceSummaryVO::setPushTimeStr, DateUtil.formatDateTime(announceBO.getPushTime()))
                        .with(AnnounceSummaryVO::setPushTime, announceBO.getPushTime())
                        .with(AnnounceSummaryVO::setPushBy, announceBO.getPushBy())
                        .with(AnnounceSummaryVO::setContent, announceBO.getContent())
                        .build();
                list.add(vo);
            }
            response.setData(list);
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }

    }
}
