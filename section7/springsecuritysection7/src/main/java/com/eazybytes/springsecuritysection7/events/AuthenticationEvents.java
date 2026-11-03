package com.eazybytes.springsecuritysection7.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    private static final Logger log =
            (Logger) LoggerFactory.getLogger(AuthenticationEvents.class);

    @EventListener
    public void onSuccess(
            AuthenticationSuccessEvent authenticationSuccessEvent) {

        log.info(
                "Authentication successful for user: {}",
                authenticationSuccessEvent.getAuthentication().getName()
        );
    }

    @EventListener
    public void onFailure(
            AbstractAuthenticationFailureEvent abstractAuthenticationFailureEvent) {

        log.info(
                "Authentication failed for user: {} due to: {}",
                abstractAuthenticationFailureEvent.getAuthentication().getName(),
                abstractAuthenticationFailureEvent.getException().getMessage()
        );
    }
}
