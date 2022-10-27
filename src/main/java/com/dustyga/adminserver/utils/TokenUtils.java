package com.dustyga.adminserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    // 生存时间
    private static final long EXPIRE_DATE = 60 * 60 * 24;

    //密钥
    private static final String TOKEN_SECRET = "nMMrvqkGt75hVEYAbcU6";

    public static String generate(String id) {
        String token = "";
        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);

            //加密
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");

            token = JWT.create()
                    .withHeader(header)
                    .withClaim("id", id)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            //TODO: 修改为自定义异常
            e.printStackTrace();
            return null;
        }

        return token;
    }
}
