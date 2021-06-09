package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.router.admin.ClearCacheApiUrlConfig;
import cn.jianwoo.blog.dto.request.ClearCacheRequest;
import cn.jianwoo.blog.dto.response.CacheResponse;
import cn.jianwoo.blog.dto.response.WebconfResponse;
import cn.jianwoo.blog.dto.response.vo.CacheVO;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ClearCacheBizService;
import cn.jianwoo.blog.service.bo.CacheBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 14:47
 */

@RestController
@RequestMapping(ClearCacheApiUrlConfig.URL_PREFIX)
@Slf4j
public class ClearCacheApiController extends BaseController {
    @Autowired
    private ClearCacheBizService clearCacheBizService;

    /**
     * 清除缓存(清除缓存页面)<br/>
     * url:/api/admin/cache/clear<br/>
     *
     * @param param JSON 参数({@link ClearCacheRequest})
     *              temp<br/>
     *              log<br/>
     *              cache<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ClearCacheApiUrlConfig.URL_COMMENT_ADD)
    public String clear(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ClearCacheRequest request = this.convertParam(param, ClearCacheRequest.class);
            clearCacheBizService.clearCache(request.getCache(), request.getLog(), request.getTemp());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

    /**
     * 获取前台首页菜单<br/>
     * url:/api/admin/webconf/info<br/>
     *
     * @return 返回响应 {@link WebconfResponse}
     * status<br/>
     * data<br/>
     * --tempSize<br/>
     * --cacheSize<br/>
     * --logSize<br/>
     * @author gulihua
     */
    @GetMapping(ClearCacheApiUrlConfig.URL_COMMENT_INFO)
    public String getWebConfig() {
        CacheResponse response = CacheResponse.getInstance();
        try {
            CacheBO cacheBO = clearCacheBizService.queryCacheInfo();
            CacheVO vo = new CacheVO();
            BeanUtils.copyProperties(cacheBO, vo);
            response.setData(vo);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

        return super.responseToJSONString(response);

    }
}
