package cn.jianwoo.blog.controller.main.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BasePageRequestDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.MsgMainApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.response.MsgSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.MsgSummaryVO;
import cn.jianwoo.blog.service.biz.MsgBizService;
import cn.jianwoo.blog.service.bo.MsgProfileBO;
import cn.jianwoo.blog.util.JwUtil;
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
 * @date 2022-08-02 17:36
 */
@RestController
@RequestMapping(MsgMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class MsgMainApiController extends BaseController {
    @Autowired
    private MsgBizService msgBizService;


    /**
     * 页面定时获取最新消息(首页)<br/>
     * url:/api/msg/timer/newest/query/list<br/>
     *
     * @param param JSON 参数({@link BasePageRequestDto})<br/>
     * @return 返回响应 {@link MsgSummaryResponse}<br/>
     * status<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --content<br/>
     * --link<br/>
     * --isRead<br/>
     * --sendTimeDesc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(MsgMainApiUrlConfig.URL_MSG_TIMER_NEWEST_QUERY_LIST)
    public String queryMsgTimerList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            List<MsgProfileBO> resList = msgBizService.queryMsgTimerMainList(10, JwUtil.getRealIpAddress(request));

            MsgSummaryResponse response = MsgSummaryResponse.getInstance();
            List<MsgSummaryVO> list = new ArrayList<>();
            for (MsgProfileBO mgProfileBO : resList) {
                MsgSummaryVO vo = JwBuilder.of(MsgSummaryVO::new)
                        .with(MsgSummaryVO::setOid, mgProfileBO.getOid())
                        .with(MsgSummaryVO::setTitle, mgProfileBO.getMsgTitle())
                        .with(MsgSummaryVO::setContent, mgProfileBO.getMsgContent())
                        .with(MsgSummaryVO::setLink, mgProfileBO.getMsgLink())
                        .with(MsgSummaryVO::setSendTime, mgProfileBO.getSendTime())
                        .with(MsgSummaryVO::setIsRead, mgProfileBO.getFlagRead() ? Constants.TRUE_1 : Constants.FALSE_0)
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
