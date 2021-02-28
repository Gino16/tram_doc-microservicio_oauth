package com.tramite_documentario.microservicios.backend.microserviciooauth.services.persona;

import com.tramite_documentario.microservicio.backend.commonpersonas.models.entity.Persona;

public interface PersonaService {
    public Persona findPersonaByDni(String dniRuc);
}
