package com.devoralime.filemanagger.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs23131";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SWAGGER = "/swagger-ui.html";
    public static final String BASE_LOGIN_URL = "/login";
    public static final String REGISTER = "/users/register";
}
