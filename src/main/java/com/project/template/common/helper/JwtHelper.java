package com.project.template.common.helper;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Jwt辅助工具
 * 可用于生成token
 * 从token中获取userId
 * 获取userName
 */
@Component
public class JwtHelper {
    // token过期时间
    private static long tokenExpiration;

    // jwt加密密钥
    private static String tokenSignKey;

    @Value("${template.token.expiration}")
    private void setTokenExpiration(long tokenExpiration) {
        JwtHelper.tokenExpiration = (tokenExpiration != 0) ? tokenExpiration : 60 * 60L;
    }

    @Value("${template.token.sign-key}")
    private void setTokenSignKey(String tokenSignKey) {
        JwtHelper.tokenSignKey = StringUtils.isNotBlank(tokenSignKey) ? tokenSignKey : "nan1mono";
    }


    public static final String USER_ID = "userId";

    public static final String NICKNAME = "nickname";

    public static final String USERNAME = "username";

    public static final String REAL_NAME = "realName";

    /**
     * 创建令牌
     *
     * @param userId   用户id
     * @param username 账号/用户名
     * @param nickname 昵称
     * @param realName 真实姓名
     * @return {@link String}
     */
    public static String createToken(Long userId, Object username, Object nickname, Object realName) {
        tokenExpiration = tokenExpiration * 1000;
        return Jwts.builder()
                .setSubject("nan1mono")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim(USER_ID, userId)
                .claim(USERNAME, username)
                .claim(NICKNAME, nickname)
                .claim(REAL_NAME, realName)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 获取UserId
     *
     * @param token 令牌
     * @return {@link Object}
     */
    public static Object getUserId(String token) {
        return decrypt(token).get(USER_ID);
    }

    /**
     * 获取Username
     *
     * @param token 令牌
     * @return {@link Object}
     */
    public static Object getUsername(String token) {
        return decrypt(token).get(USERNAME);
    }

    /**
     * 获取Nickname
     *
     * @param token 令牌
     * @return {@link Object}
     */
    public static Object getNickname(String token) {
        return decrypt(token).get(NICKNAME);
    }

    /**
     * 获取RealName
     *
     * @param token 令 牌
     * @return {@link Object}
     */
    public static Object getRealName(String token) {
        return decrypt(token).get(REAL_NAME);
    }

    /**
     * 解密
     *
     * @param token 加密令牌
     * @return {@link Claims}
     */
    private static Claims decrypt(String token) {
        if (StringUtils.isEmpty(token)) return new DefaultClaims();
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    // 仅用于测试
    public static void main(String[] args) {
//        String token = JwtHelper.createToken("1", "55");
//        System.out.println(token);
//        System.out.println(JwtHelper.getUserId(token));
//        System.out.println(JwtHelper.getUserName(token));
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encode = bCryptPasswordEncoder.encode("123456");
//        System.out.println(encode);
    }

}
