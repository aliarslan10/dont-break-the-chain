package com.example.aliarslan.dontbreakthechain.security;

public class JWTConfig {
    public static final String SECRET_KEY = "aliarslan";
    public static final String TOKEN_PREFIX = "ali ";
    public static final String HEADER_STRING = "Authorization";
    public static final int EXPIRATION_TIME = 2600000;
}
