package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BasePageRequestDto;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.BizLogApiUrlConfig;
import cn.jianwoo.blog.dto.response.LogSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.LogSummaryVO;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.enums.LoginEventTypeEnum;
import cn.jianwoo.blog.enums.ProcessStatusEnum;
import cn.jianwoo.blog.service.biz.LogBizService;
import cn.jianwoo.blog.service.bo.BizLogBO;
import cn.jianwoo.blog.service.param.PageParam;
import cn.jianwoo.blog.util.DomainUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-31 16:06
 */
@RestController
@RequestMapping(BizLogApiUrlConfig.URL_PREFIX)
@Slf4j
public class LogApiController extends BaseController {
    @Autowired
    private LogBizService logBizService;


    /**
     * 分页查询前1000个登录日志<br/>
     * url:/api/admin/log/login/query/list<br/>
     *
     * @param param JSON 参数({@link BasePageRequestDto})<br/>
     * @return 返回响应 {@link LogSummaryResponse}<br/>
     * code<br/>
     * count<br/>
     * data<br/>
     * --loginId<br/>
     * --eventType<br/>
     * --triggerTime<br/>
     * --triggerIp<br/>
     * --triggerArea<br/>
     * --processStatus<br/>
     * --failedReason<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(BizLogApiUrlConfig.URL_TAG_LOGIN_QUERY_LIST)
    public String queryLoginLogPageList(BasePageRequestDto param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));
            PageParam pageParam = new PageParam();
            pageParam.setPageNo(param.getPage());
            pageParam.setPageSize(param.getLimit());
            PageInfo<BizLogBO> pageInfo = logBizService.queryLoginLogPageList(pageParam);
            List<LogSummaryVO> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
                for (BizLogBO bizLogBO : pageInfo.getList()) {
                    LogSummaryVO vo = new LogSummaryVO();
                    vo.setLoginId(bizLogBO.getLoginId());
                    vo.setEventType(LoginEventTypeEnum.descOfValue(bizLogBO.getEventType()));
                    vo.setTriggerTime(bizLogBO.getTriggerTime());
                    vo.setTriggerIp(bizLogBO.getTriggerIp());
                    vo.setTriggerArea(bizLogBO.getTriggerArea());
                    vo.setProcessStatusStr(ProcessStatusEnum.descOfValue(bizLogBO.getProcessStatus()));
                    vo.setProcessStatus(bizLogBO.getProcessStatus());
                    vo.setFailedReason(bizLogBO.getFailedReason());
                    list.add(vo);
                }
            }
            LogSummaryResponse response = LogSummaryResponse.getInstance();
            response.setData(list);
            response.setCount(pageInfo.getTotal());
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }
    }


    /**
     * 分页查询前1000个业务日志<br/>
     * url:/api/admin/log/biz/query/list<br/>
     *
     * @param param JSON 参数({@link BasePageRequestDto})<br/>
     * @return 返回响应 {@link LogSummaryResponse}<br/>
     * code<br/>
     * count<br/>
     * data<br/>
     * --loginId<br/>
     * --eventType<br/>
     * --optType<br/>
     * --optEntityOid<br/>
     * --optEntityDesc<br/>
     * --triggerTime<br/>
     * --triggerIp<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(BizLogApiUrlConfig.URL_TAG_BIZ_QUERY_LIST)
    public String queryBizLogPageList(BasePageRequestDto param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));
            PageParam pageParam = new PageParam();
            pageParam.setPageNo(param.getPage());
            pageParam.setPageSize(param.getLimit());
            PageInfo<BizLogBO> pageInfo = logBizService.queryBizLogPageList(pageParam);
            List<LogSummaryVO> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
                for (BizLogBO bizLogBO : pageInfo.getList()) {
                    LogSummaryVO vo = new LogSummaryVO();
                    vo.setLoginId(bizLogBO.getLoginId());
                    vo.setEventType(BizEventTypeEnum.descOfValue(bizLogBO.getEventType()));
                    vo.setOptType(BizEventOptTypeEnum.descOfValue(bizLogBO.getOptType()));
                    vo.setTriggerTime(bizLogBO.getTriggerTime());
                    vo.setTriggerIp(bizLogBO.getTriggerIp());
                    vo.setOptEntityDesc(bizLogBO.getOptEntityDesc());
                    list.add(vo);
                }
            }
            LogSummaryResponse response = LogSummaryResponse.getInstance();
            response.setData(list);
            response.setCount(pageInfo.getTotal());
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }
    }
}
