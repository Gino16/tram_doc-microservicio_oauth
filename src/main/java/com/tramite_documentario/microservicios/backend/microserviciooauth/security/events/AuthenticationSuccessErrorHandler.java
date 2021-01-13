package com.tramite_documentario.microservicios.backend.microserviciooauth.security.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("authenticationSuccessErrorHandler")
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger logger = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String mensaje = "Logeado con exito" + user.getUsername();
        System.out.println(mensaje);
        logger.info(mensaje);
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        String mensaje = "Error en logeado " + e.getMessage();
        System.out.println(mensaje);
        logger.error(mensaje);
    }
}
