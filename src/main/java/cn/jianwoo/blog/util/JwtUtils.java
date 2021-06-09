package cn.jianwoo.blog.util;

import cn.jianwoo.blog.constants.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-06 14:32
 */
public class JwtUtils {

    // 签名密钥（高度保密）
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("application");
    public static String secret = RESOURCE_BUNDLE.getString("jwt.token.secret");

    // 签名算法
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    // token前缀
    private static final String TOKEN_PREFIX = "Bearer ";


    /**
     * 生成JWT token
     *
     * @param map         传入数据
     * @param validPeriod 有效期（单位：ms）
     * @author gulihua
     */
    public static String sign(Map<String, Object> map, long validPeriod) {
        return sign(map, null, null, validPeriod);
    }

    /**
     * 生成JWT token
     *
     * @param map         传入数据
     * @param issuer      签发者
     * @param subject     面向用户
     * @param validPeriod 有效期（单位：ms）
     * @return
     * @author gulihua
     */
    public static String sign(Map<String, Object> map, String issuer, String subject,
                              long validPeriod) {
        Date now = DateUtil.getNow();
        String jwt = Jwts.builder()
                .setClaims(map)// 设置自定义数据
                .setIssuedAt(now)// 设置签发时间
                .setExpiration(DateUtil.add(now, validPeriod, TimeUnit.MILLISECONDS))// 设置过期时间
                .setIssuer(issuer) // 设置签发者
                .setSubject(subject) // 设置面向用户
                .signWith(signatureAlgorithm, secret)
                .compact();
        return TOKEN_PREFIX + jwt;
    }

    public static Map<String, Object> parse(String token) {
        try {
            return Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, Constants.BLANK))
                    .getBody();
        } catch (Exception e) {
            throw new IllegalStateException("Token验证失败：" + e.getMessage());
        }
    }
}
