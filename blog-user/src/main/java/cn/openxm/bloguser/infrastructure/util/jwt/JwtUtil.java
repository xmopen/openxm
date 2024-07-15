package cn.openxm.bloguser.infrastructure.util.jwt;

import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.common.time.LocalTime;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt Util.
 *
 * @author Xiao Ma
 * @date 2024/7/15
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Component
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * JWT_PUBLIC_CLAIM_KEY_MAIL is a JWT personal information key.
     * */
    private static final String JWT_PUBLIC_CLAIM_KEY_MAIL = "sub";

    /**
     * JWT_PUBLIC_CLAIM_KEY_CREATED is the creation time of the jwt.
     * */
    private static final String JWT_PUBLIC_CLAIM_KEY_CREATED = "created";


    /**
     * TODO: cannot obtain the value from spring context.
     * */
    private final String secret = "openxmcnuuuuiiijjjkkkllxxx";

    /**
     * TODO: cannot obtain the value from spring context.
     * */
    private final Long expiration = 259200L;


    /**
     * generateTokenWithUserData generate a unique token with the user's identity information.
     * */
    public String generateTokenWithUserData(UserEntity userData){
        Map<String, Object> claims = new HashMap<>();
        claims.put(JWT_PUBLIC_CLAIM_KEY_MAIL,userData.getToken());
        claims.put(JWT_PUBLIC_CLAIM_KEY_CREATED, new Date());
        return this.generateToken(claims);
    }


    /**
     * generateToken generate a unique token.
     * HMAC: generates signature using hash functions and secret.
     * RSA: generate signature using private/public secret.
     * */
    private String generateToken(Map<String, Object> claims){
        LOGGER.info("claims: {} secret:{} exp:{}",claims,secret,expiration);
        return Jwts.builder().
                setClaims(claims).
                setExpiration(this.getJwtExpiration()).
                signWith(SignatureAlgorithm.HS512,this.secret)
                .compact();
    }


    /**
     * getJwtExpiration get the expiration date of jwt.
     * */
    private Date getJwtExpiration(){
        return new Date(LocalTime.getCurrenTimeWithMillis() + expiration * 1000);
    }

    /**
     * getClaimsFromJwtToken resolve the claims context from the token.
     *
     * @param token of jwt.
     * @return claims if the value is null,the token is invalid.
     * */
    public Claims getClaimsFromJwtToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOGGER.error("jwts parse token error:{}, token:{}",e.getMessage(),token);
        }
        return claims;
    }

    /**
     * validateToken verifies whether the token expires.
     *
     * @param token is a JWT Token.
     * @return true indicates that the token has expired.
     * */
    public boolean validateToken(String token){
        return this.getClaimsFromJwtToken(token).getExpiration().before(new Date());
    }

    /**
     * validateToken verifies whether the token expires.
     *
     * @param claims is a JWT carrier symbol.
     * @return true indicates that the token has expired.
     * */
    public boolean validateToken(Claims claims){
        return claims.getExpiration().before(new Date());
    }

    /**
     * getUserToken get user token from JWT Token.
     *
     * @param claims is a JWT carrier symbol.
     * @return the user token.
     * */
    public String getUserToken(Claims claims){
        return claims.getSubject();
    }

}






























