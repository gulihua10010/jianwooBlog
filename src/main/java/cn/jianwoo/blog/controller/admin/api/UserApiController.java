package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.UserApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.PasswordChangeRequest;
import cn.jianwoo.blog.dto.request.UserEditInfoRequest;
import cn.jianwoo.blog.dto.request.UserInfoRequest;
import cn.jianwoo.blog.dto.response.UserResponse;
import cn.jianwoo.blog.dto.response.vo.AdminUserInfoVO;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private AdminBizService adminBizService;
    @Value("${admin.name}")
    private String adminName;

    @Autowired
    private CacheStore<String, String> jwCacheStore;

    /**
     * 获取管理员用户信息<br/>
     * url:/api/admin/user/info<br/>
     *
     * @return 返回响应 {@link UserResponse}<br/>
     * status<br/>
     * data<br/>
     * --nickName<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(UserApiUrlConfig.URL_USER_INFO)
    public String queryUserInfo(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            UserInfoRequest request = this.convertParam(param, UserInfoRequest.class);
            BizValidation.paramValidate(request.getLoginID(), "loginID", "用户登录ID不能为空!");

            AdminBO admin = adminBizService.queryAdminInfoByLoginId(request.getLoginID().trim());
            AdminUserInfoVO vo = JwBuilder.of(AdminUserInfoVO::new)
                    .with(AdminUserInfoVO::setNickName, Constants.ADMIN_CN).build();
            if (null != admin) {
                vo.setNickName(admin.getUserNick());
            }
            UserResponse response = UserResponse.getInstance();
            response.setData(vo);

            return super.responseToJSONString(response);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

    }

    /**
     * 获取管理员编辑用户信息<br/>
     * url:/api/admin/user/profile/edit<br/>
     *
     * @return 返回响应 {@link UserResponse}<br/>
     * status<br/>
     * data<br/>
     * --ip<br/>
     * --nickName<br/>
     * --email<br/>
     * --sex<br/>
     * --userPhone<br/>
     * --avatarSrc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(UserApiUrlConfig.URL_USER_PROFILE_EDIT)
    public String queryUserEditInfo(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            UserInfoRequest request = this.convertParam(param, UserInfoRequest.class);
            BizValidation.paramValidate(request.getLoginID(), "loginID", "用户登录ID不能为空!");
//            if (StringUtils.isBlank(request.getLoginID()))
//            {
//                request.setLoginID(adminName);
//            }
            AdminBO admin = adminBizService.queryAdminInfoByLoginId(request.getLoginID().trim());
            AdminUserInfoVO vo = new AdminUserInfoVO();
            if (null != admin) {
                vo.setLoginID(admin.getUsername());
                vo.setNickName(admin.getUserNick());
                vo.setUserEmail(admin.getUserEmail());
                vo.setUserPhone(admin.getUserPhone());
                vo.setUserSex(admin.getUserSex());
                vo.setAvatarSrc(admin.getAvatarSrc());
            }
            UserResponse response = UserResponse.getInstance();
            response.setData(vo);

            return super.responseToJSONString(response);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

    }


    /**
     * 保存用户信息<br/>
     * url:/api/admin/user/profile/edit/update<br/>
     *
     * @param param JSON 参数({@link UserEditInfoRequest})<br/>
     *              loginID<br/>
     *              userNick<br/>
     *              userEmail<br/>
     *              userPhone<br/>
     *              userSex<br/>
     *              avatarSrc<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PageId(PageIdEnum.ADMIN_PROFILE)
    @SubToken
    @PostMapping(UserApiUrlConfig.URL_USER_PROFILE_EDIT_UPDATE)
    public String doSaveEditInfo(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            UserEditInfoRequest request = this.convertParam(param, UserEditInfoRequest.class);
            BizValidation.paramValidate(request.getLoginID(), "loginID", "用户登录ID不能为空!");
            BizValidation.paramValidate(request.getUserNick(), "userNick", "用户昵称不能为空!");
            BizValidation.paramValidate(request.getUserEmail(), "userEmail", "用户邮箱不能为空!");
            BizValidation.paramLengthValidate(request.getUserNick(), 20, "userNick", "用户昵称不能大于20个字符!");
            BizValidation.paramLengthValidate(request.getUserEmail(), 30, "userEmail", "用户邮箱不能大于30个字符!");
            BizValidation.paramLengthValidate(request.getUserPhone(), 11, "userPhone", "用户手机号不能大于11个字符!");
            BizValidation.paramRangeValidate(request.getUserSex(), "userSex", "性别值必须在[10,20]中!", "10", "20");
            BizValidation.paramRegexValidate(request.getUserEmail(), Constants.EMAIL_REGEX,"userEmail", "用户邮箱格式不正确!");
            BizValidation.paramRegexValidate(request.getUserPhone(), Constants.PHONE_REGEX,"userPhone", "用户手机号格式不正确!");
            AdminBO adminBO = JwBuilder.of(AdminBO::new)
                    .with(AdminBO::setUsername, request.getLoginID())
                    .with(AdminBO::setUserEmail, request.getUserEmail())
                    .with(AdminBO::setUserPhone, request.getUserPhone())
                    .with(AdminBO::setUserSex, request.getUserSex())
                    .with(AdminBO::setUserNick, request.getUserNick())
                    .with(AdminBO::setAvatarSrc, request.getAvatarSrc())
                    .build();
            adminBizService.doSaveEditInfo(adminBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    /**
     * 修改密码<br/>
     * url:/api/admin/user/password/change<br/>
     *
     * @param param JSON 参数({@link PasswordChangeRequest})<br/>
     *              loginID<br/>
     *              oldPassword<br/>
     *              newPassword<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PageId(PageIdEnum.ADMIN_PASSWORD)
    @SubToken
    @PostMapping(UserApiUrlConfig.URL_USER_PASSWORD_CHANGE)
    public String doChangePassword(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            PasswordChangeRequest request = this.convertParam(param, PasswordChangeRequest.class);
            BizValidation.paramValidate(request.getLoginID(), "loginID", "登录ID不能为空!");
            BizValidation.paramValidate(request.getOldPassword(), "oldPassword", "旧密码不能为空!");
            BizValidation.paramValidate(request.getNewPassword(), "newPassword", "新密码不能为空!");
            BizValidation.paramMinLengthValidate(request.getNewPassword(), 6, "newPassword", "密码不能小于6位!");
            BizValidation.paramLengthValidate(request.getNewPassword(), 20, "newPassword", "密码不能小于6位!");
            BizValidation.paramRegexValidate(request.getNewPassword(), Constants.PASSWORD_REGEX,"newPassword", "密码必须包含数字,英文,字符中的两种以上,长度6-20!");
            adminBizService.doChangePassword(request.getLoginID(), request.getOldPassword(), request.getNewPassword());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }



}
