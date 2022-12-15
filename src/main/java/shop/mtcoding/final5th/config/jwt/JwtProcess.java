package shop.mtcoding.final5th.config.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import shop.mtcoding.final5th.config.auth.LoginUser;

public class JwtProcess {

    public static String create(LoginUser loginUser) {
        String jwtToken = JWT.create()
                .withSubject(loginUser.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("userId", loginUser.getUserId())
                .withClaim("userName", loginUser.getUserName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }

    public static Long verify(String jwtToken) {
        DecodedJWT decodeJwt = JWT.require(Algorithm.HMAC512(JwtProps.SECRET)).build().verify(jwtToken);
        System.out.println("존왓탱" + decodeJwt);
        Long userId = decodeJwt.getClaim("userId").asLong();
        return userId;
    }
}
