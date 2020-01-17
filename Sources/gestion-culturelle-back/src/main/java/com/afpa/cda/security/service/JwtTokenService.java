package com.afpa.cda.security.service;

import org.springframework.security.core.Authentication;

import com.afpa.cda.security.model.JwtTokens;
import com.afpa.cda.security.model.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtTokenService {

    JwtTokens createTokens(Authentication authentication);
    String createToken(UserDto user);
    String createRefreshToken(UserDto user);

    JwtTokens refreshJwtToken(String token);
    Jws<Claims> validateJwtToken(String token);

}
