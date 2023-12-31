package com.example.BloggingAppAPI.Security;

import com.example.BloggingAppAPI.users.UsersService;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTAuthenticationManager implements AuthenticationManager {

    private JWTService jwtService;
    private UsersService usersService;
    public JWTAuthenticationManager(JWTService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication) {
            var jwtAuthentication = (JWTAuthentication) authentication;
            var jwt = jwtAuthentication.getCredentials();
            var userId = jwtService.retrieveUserId(jwt);
            var userEntity = usersService.getUser(userId);

            jwtAuthentication.userEntity = userEntity;
            jwtAuthentication.setAuthenticated(true);

            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
            return jwtAuthentication;
        }
        throw new IllegalAccessError("connaot authenticate non-JWT authentication");
    }
}
