package it.theboys.project0002api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.theboys.project0002api.model.SecUserDetails;
import it.theboys.project0002api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .antMatchers("/api/v1/user/sign**").permitAll()
                .anyRequest().authenticated();

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
        private AuthenticationManager manager;

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
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            SecUserDetails u= (SecUserDetails) authResult.getPrincipal();
            Algorithm a=Algorithm.HMAC256("ecchi".getBytes());
            String access= JWT.create()
                    .withSubject(u.getUsername())
                    .withExpiresAt(new Date((System.currentTimeMillis()+10*60*1000)))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", u.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(a);
            String refresh= JWT.create()
                    .withSubject(u.getUsername())
                    .withExpiresAt(new Date((System.currentTimeMillis()+5*60*60*1000)))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", u.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(a);
            Map<String, String> responseBody= new HashMap<>();

            responseBody.put("access_token",access);
            responseBody.put("refresh_token",refresh);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
        }
    }

}
