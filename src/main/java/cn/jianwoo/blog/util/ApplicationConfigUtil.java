package cn.jianwoo.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gulihua
 * @Description
 * @date 2022-08-04 15:07
 */
@Slf4j
@Component
public class ApplicationConfigUtil {

    @Value("${access.token.expired.seconds}")
    private String accessTokenExpiredSeconds;

    @Value("${refresh.token.expired.days}")
    private String refreshTokenExpiredDays;

    @Value("${jwt.token.secret}")
    private String jwtTokenSecret;

    @Value("${aes.secret}")
    private String aesSecret;

    @Value("${user.profile.avatar}")
    private String userProfileAvatar;

    public String getAccessTokenExpiredSeconds() {
        return this.accessTokenExpiredSeconds;
    }

    public void setAccessTokenExpiredSeconds(String accessTokenExpiredSeconds) {
        this.accessTokenExpiredSeconds = accessTokenExpiredSeconds;
    }

    public String getRefreshTokenExpiredDays() {
        return this.refreshTokenExpiredDays;
    }

    public void setRefreshTokenExpiredDays(String refreshTokenExpiredDays) {
        this.refreshTokenExpiredDays = refreshTokenExpiredDays;
    }

    public String getJwtTokenSecret() {
        return this.jwtTokenSecret;
    }

    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    public String getAesSecret() {
        return this.aesSecret;
    }

    public void setAesSecret(String aesSecret) {
        this.aesSecret = aesSecret;
    }

    public String getUserProfileAvatar() {
        return this.userProfileAvatar;
    }

    public void setUserProfileAvatar(String userProfileAvatar) {
        this.userProfileAvatar = userProfileAvatar;
    }
}
