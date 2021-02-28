package com.tramite_documentario.microservicios.backend.microserviciooauth.services.usuario;

import com.tramite_documentario.microservicios.backend.commonusuarios.models.entity.Usuario;

public interface UsuarioService {
    public Usuario findByUsername(String username);
}
