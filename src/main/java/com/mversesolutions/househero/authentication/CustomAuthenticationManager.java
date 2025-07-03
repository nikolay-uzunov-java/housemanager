package com.mversesolutions.househero.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;

public class CustomAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticationResult = null;
        // TODO: Perform authentication checks...

        // Erase credentials post-check
        if (authenticationResult instanceof CredentialsContainer container) {
            container.eraseCredentials();
        }

        return authenticationResult;
    }
}
