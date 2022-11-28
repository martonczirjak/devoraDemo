package com.devoralime.filemanagger.security;

import com.auth0.jwt.JWT;
import com.devoralime.filemanagger.user.model.DevoraUserEntity;
import com.devoralime.filemanagger.user.repository.DevoraUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.devoralime.filemanagger.security.SecurityConstants.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private DevoraUserRepository userRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, DevoraUserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginDto creds = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginDto.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserName(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String preUser = ((User) auth.getPrincipal()).getUsername();
        DevoraUserEntity byUsername = userRepository.findByUserName(preUser).get();
        String token = JWT.create()
                .withSubject(preUser)
                .withClaim("userId", byUsername.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        //Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(loginResponse);
       // String resJson = gson.toJson(loginResponse);
        res.getWriter().write(s);
    }
}