package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.BlackIpApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.BlackIpPageRequest;
import cn.jianwoo.blog.dto.request.BlackIpRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.BackIpSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.BlackIpSummaryVO;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.service.biz.BlackIpBizService;
import cn.jianwoo.blog.service.bo.BlackIpBO;
import cn.jianwoo.blog.service.param.BlackIpParam;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-06-30 11:18
 */
@RestController
@RequestMapping(BlackIpApiUrlConfig.URL_PREFIX)
@Slf4j
public class BlackIpApiController extends BaseController {

    @Autowired
    private BlackIpBizService blackIpBizService;

    /**
     * 分页查询网站IP黑名单<br/>
     * <p>
     * url:/api/admin/black/ip/query/list<br/>
     *
     * @param param JSON 参数({@link BlackIpPageRequest})<br/>
     *              ip<br/>
     * @return 返回响应 {@link BackIpSummaryResponse}<br/>
     * status<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --ip<br/>
     * --createdTimeDesc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(BlackIpApiUrlConfig.URL_BLACK_IP_QUERY_LIST)
    public String queryBlackIpListPage(BlackIpPageRequest param) {
        try {
            super.printRequestParams(DomainUtil.toString(param));

            BlackIpParam pageParam = new BlackIpParam();
            pageParam.setPageNo(param.getPage());
            pageParam.setPageSize(param.getLimit());
            if (StringUtils.isNotBlank(param.getIp())) {
                pageParam.setIp(param.getIp().trim());
            }
            PageInfo<BlackIpBO> pageInfo = blackIpBizService.queryAllIpBlackListPage(pageParam);

            BackIpSummaryResponse response = BackIpSummaryResponse.getInstance();
            List<BlackIpSummaryVO> list = new ArrayList<>();
            for (BlackIpBO blackIpBO : pageInfo.getList()) {
                BlackIpSummaryVO vo = JwBuilder.of(BlackIpSummaryVO::new)
                        .with(BlackIpSummaryVO::setOid, blackIpBO.getOid())
                        .with(BlackIpSummaryVO::setIp, blackIpBO.getAccessIp())
                        .with(BlackIpSummaryVO::setCreatedTime, blackIpBO.getCreateTime())
                        .with(BlackIpSummaryVO::setCreatedTimeStr, DateUtil.formatDateTime(blackIpBO.getCreateTime()))
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


    /**
     * 添加黑名单<br/>
     * url:/api/admin/black/ip/add<br/>
     *
     * @param param JSON 参数({@link BlackIpRequest})<br/>
     *              ip<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PageId(PageIdEnum.BLACK_IP_CREATE)
    @PostMapping(BlackIpApiUrlConfig.URL_BLACK_IP_ADD)
    public String doAddBlackIp(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            BlackIpRequest request = this.convertParam(param, BlackIpRequest.class);
            BizValidation.paramValidate(request.getIp(), "ip", "IP不能为空!");
            BizValidation.paramRegexValidate(request.getIp(), Constants.IPV4_REGEX, "ip", "Ipv4格式不正确!");

            blackIpBizService.doAddBlackIp(request.getIp());
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 从黑名单移除<br/>
     * url:/api/admin/black/ip/remove<br/>
     *
     * @param param JSON 参数({@link BlackIpRequest})<br/>
     *              ip<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(BlackIpApiUrlConfig.URL_BLACK_IP_REMOVE)
    public String doRemoveBlackIp(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            BlackIpRequest request = this.convertParam(param, BlackIpRequest.class);
            BizValidation.paramValidate(request.getIp(), "ip", "IP不能为空!");

            blackIpBizService.doRemoveBlackIp(request.getIp());
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 批量从黑名单移除<br/>
     * url:/api/admin/black/ip/remove/list<br/>
     *
     * @param param JSON 参数({@link BlackIpRequest})<br/>
     *              ipList<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(BlackIpApiUrlConfig.URL_BLACK_IP_REMOVE_LIST)
    public String doRemoveBlackIpList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            BlackIpRequest request = this.convertParam(param, BlackIpRequest.class);
            BizValidation.paramValidate(request.getIpList(), "ipList", "IP 列表不能为空!");
            for (String ip : request.getIpList()) {
                BizValidation.paramValidate(ip, "ip", "IP不能为空!");
                BizValidation.paramRegexValidate(ip, Constants.IPV4_REGEX, "ip", "Ipv4格式不正确!");

            }
            blackIpBizService.doRemoveBlackIpList(request.getIpList());
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 批量添加黑名单<br/>
     * url:/api/admin/black/ip/add/list<br/>
     *
     * @param param JSON 参数({@link BlackIpRequest})<br/>
     *              ipList<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(BlackIpApiUrlConfig.URL_BLACK_IP_ADD_LIST)
    @PageId(PageIdEnum.BLACK_IP_CREATE)
    public String doAddBlackIpList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            BlackIpRequest request = this.convertParam(param, BlackIpRequest.class);
            BizValidation.paramValidate(request.getIpList(), "ipList", "IP列表不能为空!");
            for (String ip : request.getIpList()) {
                BizValidation.paramValidate(ip, "ip", "IP不能为空!");
                BizValidation.paramRegexValidate(ip, Constants.IPV4_REGEX, "ip", "Ipv4格式不正确!");

            }

            blackIpBizService.doAddBlackIpList(request.getIpList());
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } catch (Exception e) {
            return super.exceptionToString(e);
        }

    }
}
