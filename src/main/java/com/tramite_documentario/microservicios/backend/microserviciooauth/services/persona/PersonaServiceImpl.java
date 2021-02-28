package com.tramite_documentario.microservicios.backend.microserviciooauth.services.persona;

import com.tramite_documentario.microservicio.backend.commonpersonas.models.entity.Persona;
import com.tramite_documentario.microservicios.backend.microserviciooauth.clients.PersonaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private PersonaFeignClient personaFeignClient;

    @Override
    public Persona findPersonaByDni(String dniRuc) {
        return personaFeignClient.findPersonaByDni(dniRuc);
    }
}
