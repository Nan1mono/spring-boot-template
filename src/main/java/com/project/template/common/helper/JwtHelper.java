package com.project.template.common.helper;

import com.project.template.common.exception.MyException;
import com.project.template.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.lang3.StringUtils;
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
    public static String createToken(Long userId, Object username, Object nickname, Object realName, long tokenExpiration, String tokenSignKey) {
        tokenExpiration = tokenExpiration == 0 ? 24 * 60 * 60 * 1000 : tokenExpiration * 1000;
        tokenSignKey = StringUtils.isNotBlank(tokenSignKey) ? tokenSignKey : "nan1mono";
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
     * @return {@link Claims}
     */
    private static Claims decrypt(String token, String tokenSignKey) {
        if (StringUtils.isEmpty(token)) return new DefaultClaims();
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new MyException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
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
