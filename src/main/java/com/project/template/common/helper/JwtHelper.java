package com.project.template.common.helper;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Jwt辅助工具
 * 可用于生成token
 * 从token中获取userId
 * 获取userName
 */
public class JwtHelper {
    // token过期时间
    private static long tokenExpiration = 24*60*60*1000;
    // jwt加密密钥
    private static String tokenSignKey = "123456";

    /**
     * 根据userId和userName生成token
     * @param userId
     * @param userName
     * @return
     */
    public static String createToken(Object userId, Object userName) {
        String token = Jwts.builder()
                .setSubject("nan1mono")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }
    
    public static Object getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Object userId = claims.get("userId");
        return userId;
    }
    
    public static Object getUserName(String token) {
        if(StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get("userName");
    }
    
    // 仅用于测试
    public static void main(String[] args) {
        String token = JwtHelper.createToken("1", "55");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUserName(token));
    }

}
