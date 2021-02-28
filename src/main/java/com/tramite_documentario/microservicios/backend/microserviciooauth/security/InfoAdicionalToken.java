package com.tramite_documentario.microservicios.backend.microserviciooauth.security;

import com.tramite_documentario.microservicio.backend.commonpersonas.models.entity.Persona;
import com.tramite_documentario.microservicios.backend.commonusuarios.models.entity.Usuario;
import com.tramite_documentario.microservicios.backend.microserviciooauth.services.persona.PersonaService;
import com.tramite_documentario.microservicios.backend.microserviciooauth.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaService personaService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();

        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        Persona persona = personaService.findPersonaByDni(usuario.getDniRuc());
        info.put("dniRuc", usuario.getDniRuc());
        info.put("nombre", persona.getNombre());
        info.put("apellido", persona.getApellidos());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
