package com.project.template.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.template.security.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.project.template.security.enums.AuthFailEnum.FETCH_USERINFO_ERROR;

/**
 * Jwt辅助工具
 * 可用于生成token
 * 从token中获取userId
 * 获取userName
 */
@Component
@Slf4j
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
        tokenExpiration = tokenExpiration * 1000;
        return JWT.create()
                .withSubject(String.valueOf(userId))
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
     * @return {@link Claim}
     */
    private static Map<String, Claim> decrypt(String token) {
        if (StringUtils.isEmpty(token)) return new HashMap<>();
        DecodedJWT decode = null;
        try {
            decode = JWT.decode(token);
        } catch (JWTDecodeException | TokenExpiredException exception) {
            // 用于校验token合法性以及token是否过期
            throw new AuthException(FETCH_USERINFO_ERROR);
        }
        return decode.getClaims();
    }

    // 仅用于测试
    public static void main(String[] args) {
        String key = "123456";
        String token = JwtHelper.createToken(1L, "lee", "nanimono", "ljc", 10000, key);
        log.info("token = {}", token);
        log.info("userId = {}", JwtHelper.getUserId(token));
        log.info("nickname = {}", JwtHelper.getNickname(token));
    }

}
