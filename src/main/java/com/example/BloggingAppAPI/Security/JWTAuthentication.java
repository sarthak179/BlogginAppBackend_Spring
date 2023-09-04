package com.example.BloggingAppAPI.Security;

import com.example.BloggingAppAPI.users.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public class JWTAuthentication implements Authentication {

    String jwt;
    UserEntity userEntity;

    public JWTAuthentication(String jwt) {
        this.jwt = jwt;
    }

    Long userId;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Return the credentials of the {@code Authentication} request
     * For eg, the password, or the bearer token, or the cookie
     * @return
     */
    @Override
    public String getCredentials() {
        return jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    /**
     * The "principal" is the entity that is being authenticated.
     * In this case it is the user.
     * @return
     */
    @Override
    public UserEntity getPrincipal() {
        return userEntity;
    }

    @Override
    public boolean isAuthenticated() {
        return (userEntity != null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
