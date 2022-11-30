package shop.mtcoding.final5th.config.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import shop.mtcoding.final5th.config.auth.LoginUser;

public class JwtProcess {

    public static String create(LoginUser loginUser) {
        String jwtToken = JWT.create()
                .withSubject(loginUser.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("userId", loginUser.getUserId())
                .withClaim("userName", loginUser.getUserName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return JwtProperties.TOKEN_PREFIX + jwtToken;
    }
}
