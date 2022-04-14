package com.volunteer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wdf
 */
@Slf4j
@Component
public class JwtUtil {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final int JWT_TOKEN_VALIDITY = 24 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    @Value(("${jwt.issuer}"))
    private String issuer;

    public String generateToken(Long userId) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(DateUtils.addSeconds(new Date(), JWT_TOKEN_VALIDITY))
                    .withClaim("userId", userId)
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public Claim getUserId(){
        String token = getToken();
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim("userId");
    }

    /**
     * 判断token 是否过期
     * @param token
     * @return
     */
    public Boolean expired(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT decode = JWT.decode(token);
        return decode.getExpiresAt().before(new Date());
    }

    /**
     * 获取当前用户token
     * @return
     */
    public String getToken(){
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        String token=request.getHeader("Authorization");
        return token;
    }


}
