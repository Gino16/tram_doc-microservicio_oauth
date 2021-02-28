package com.tramite_documentario.microservicios.backend.microserviciooauth.clients;

import com.tramite_documentario.microservicio.backend.commonpersonas.models.entity.Persona;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-personas")
public interface PersonaFeignClient {
    @GetMapping("/buscar-uno-por-dni/{dniRuc}")
    public Persona findPersonaByDni(@PathVariable String dniRuc);
}
