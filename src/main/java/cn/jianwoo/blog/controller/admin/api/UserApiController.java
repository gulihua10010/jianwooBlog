package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.router.admin.UserApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.dto.response.UserResponse;
import cn.jianwoo.blog.dto.response.vo.AdminUserInfoVO;
import cn.jianwoo.blog.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-04 11:36
 */
@RestController
@RequestMapping(UserApiUrlConfig.URL_PREFIX)
@Slf4j
public class UserApiController extends BaseController {
    @Autowired
    private AdminTransDao adminTransDao;
    @Value("${admin.name}")
    private String adminName;

    @Autowired
    private CacheStore<String, String> jwCacheStore;

    /**
     * 获取管理员用户信息<br/>
     * url:/api/admin/user/info<br/>
     *
     * @return 返回响应 {@link UserResponse}
     * status<br/>
     * count<br/>
     * data<br/>
     * --ip<br/>
     * --nickName<br/>
     * --email<br/>
     * --sex<br/>
     * @author gulihua
     */
    @GetMapping(UserApiUrlConfig.URL_USER_INFO)
    public String getInfo() {
        Admin admin = adminTransDao.queryAdminByName(adminName);
        AdminUserInfoVO vo = JwBuilder.of(AdminUserInfoVO::new)
                .with(AdminUserInfoVO::setNickName, Constants.ADMIN).build();
        if (null != admin) {
            vo.setNickName(admin.getNick());
            vo.setEmail(admin.getEmail());
            vo.setSex(admin.getSex());
        }
        UserResponse response = UserResponse.getInstance();
        response.setData(vo);

        return super.responseToJSONString(response);

    }
}
