package com.status.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BackendAdminUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public BackendAdminUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
