package cn.jianwoo.blog.controller.main.api;

import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.MenuMainApiUrlConfig;
import cn.jianwoo.blog.dto.response.BackendMenuResponse;
import cn.jianwoo.blog.dto.response.MainHomeMenuResponse;
import cn.jianwoo.blog.dto.response.vo.MainHomeMenuVO;
import cn.jianwoo.blog.dto.response.vo.MainHomeSubMenuVO;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.bo.MenuBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(MenuMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class MenuMainApiController extends BaseController {
    @Autowired
    private MenuBizService menuBizService;


    /**
     * 获取菜单信息<br/>
     * url:/api/menu/query/list<br/>
     *
     * @return 返回响应 {@link BackendMenuResponse}<br/>
     * status<br/>
     * count<br/>
     * data<br/>
     * --id<br/>
     * --title<br/>
     * --icon<br/>
     * --isCategory<br/>
     * --subList<br/>
     * ----id<br/>
     * ----name<br/>
     * ----title<br/>
     * ----url<br/>
     * ----isCategory<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(MenuMainApiUrlConfig.URL_QUERY_LIST)
    @IpLimit(key = "queryMenuList")
    public String queryMenuList() {
        MainHomeMenuResponse response = MainHomeMenuResponse.getInstance();
        List<MainHomeMenuVO> list = new ArrayList<>();
        try {
            List<MenuBO> menuExtList = menuBizService.queryEffectiveMainHomeMenuList();
            for (MenuBO menuBO : menuExtList) {
                List<MainHomeSubMenuVO> subMenuVOList = new ArrayList<>();
                List<Long> ids = new ArrayList<>();
                MainHomeMenuVO vo = new MainHomeMenuVO();
                vo.setId(menuBO.getOid());
                vo.setIcon(menuBO.getIcon());
                vo.setTitle(menuBO.getText());
                vo.setUrl(menuBO.getUrl());
                vo.setIsCategory(menuBO.getFlagCategory());
                ids.add(menuBO.getOid());
                if (CollectionUtils.isNotEmpty(menuBO.getSubMenuList())) {
                    for (MenuBO subMenu : menuBO.getSubMenuList()) {
                        MainHomeSubMenuVO subMenuVO = JwBuilder.of(MainHomeSubMenuVO::new)
                                .with(MainHomeSubMenuVO::setId, subMenu.getOid())
                                .with(MainHomeSubMenuVO::setTitle, subMenu.getText())
                                .with(MainHomeSubMenuVO::setName, subMenu.getName())
                                .with(MainHomeSubMenuVO::setUrl, subMenu.getUrl())
                                .with(MainHomeSubMenuVO::setIsCategory, subMenu.getFlagCategory())
                                .build();
                        subMenuVOList.add(subMenuVO);
                        ids.add(subMenu.getOid());
                    }
                }
                vo.setIds(ids);
                vo.setSubList(subMenuVOList);
                list.add(vo);
            }

        } catch (JwBlogException e) {
            log.error("MenuMainApiController.queryMenuList exec failed, e:\n", e);
        }
        response.setData(list);
        return super.responseToJSONString(response);

    }
}
