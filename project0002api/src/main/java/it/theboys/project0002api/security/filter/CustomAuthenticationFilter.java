package it.theboys.project0002api.security.filter;

import it.theboys.project0002api.controller.UserController;
import it.theboys.project0002api.model.SecUserDetails;
import it.theboys.project0002api.utils.AppSecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager manager;

    public CustomAuthenticationFilter(AuthenticationManager manager) {
        this.manager = manager;
        setFilterProcessesUrl("/api/v1/user/signin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String u = request.getParameter("userName");
        String p = request.getParameter("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(u, p);
        return manager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        SecUserDetails u = (SecUserDetails) authResult.getPrincipal();
        String access = new AppSecurityUtils().generateJWT(u, request.getRequestURL().toString(), 10*60*1000);
        String refresh = new AppSecurityUtils().generateJWT(u, request.getRequestURL().toString(), 5*60*60*1000);
        UserController.TokenResponse(response,refresh, access);
    }
}
