package com.project.template.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt辅助工具
 * 可用于生成token
 * 从token中获取userId
 * 获取userName
 */
@Component
public class JwtHelper {

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
    public static String createToken(Long userId, String username, String nickname, String realName, long tokenExpiration, String tokenSignKey) {
        tokenExpiration = tokenExpiration == 0 ? 24 * 60 * 60 * 1000 : tokenExpiration * 1000;
        tokenSignKey = StringUtils.isNotBlank(tokenSignKey) ? tokenSignKey : "nan1mono";
        return JWT.create()
                .withSubject("nan1mono")
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpiration))
                .withClaim(USER_ID, userId)
                .withClaim(USERNAME, username)
                .withClaim(NICKNAME, nickname)
                .withClaim(REAL_NAME, realName)
                .sign(Algorithm.HMAC256(tokenSignKey));
    }

    /**
     * 获取UserId
     *
     * @param token 令牌
     * @return {@link Object}
     */
    public static Object getUserId(String token, String tokenSignKey) {
        return decrypt(token, tokenSignKey).get(USER_ID);
    }

    /**
     * 获取Username
     *
     * @param token 令牌
     * @return {@link Object}
     */
    public static Object getUsername(String token, String tokenSignKey) {
        return decrypt(token, tokenSignKey).get(USERNAME);
    }

    /**
     * 获取Nickname
     *
     * @param token 令牌
     * @return {@link Object}
     */
    public static Object getNickname(String token, String tokenSignKey) {
        return decrypt(token, tokenSignKey).get(NICKNAME);
    }

    /**
     * 获取RealName
     *
     * @param token 令 牌
     * @return {@link Object}
     */
    public static Object getRealName(String token, String tokenSignKey) {
        return decrypt(token, tokenSignKey).get(REAL_NAME);
    }

    /**
     * 解密
     *
     * @param token 加密令牌
     * @return {@link Claim}
     */
    private static Map<String, Claim> decrypt(String token, String tokenSignKey) {
        if (StringUtils.isEmpty(token)) return new HashMap<>();
        DecodedJWT decode = JWT.decode(tokenSignKey);
        return decode.getClaims();
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
