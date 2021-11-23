package it.theboys.project0002api.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import it.theboys.project0002api.model.SecUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

public class AppSecurityUtils {
    private Algorithm a = Algorithm.HMAC256("ecchi".getBytes());
    public String generateJWT(SecUserDetails u, String i, int period){
        return JWT.create()
                .withSubject(u.getUserId())
                .withExpiresAt(new Date((System.currentTimeMillis() + 10 * 60 * 1000)))
                .withIssuer(i)
                .withClaim("userRoles", u.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withClaim( "userId", u.getUserId())
                .withClaim( "userAvatar", u.getUserAvatar())
                .withClaim( "userName", u.getUsername())
                .sign(a);
    }
}
