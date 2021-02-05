package com.tramite_documentario.microservicios.backend.microserviciooauth.clients;

import com.tramite_documentario.microservicios.backend.commonusuarios.models.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-usuarios")
public interface UsuarioFeignClient {

    @PostMapping("/usuarios")
    public Usuario save(@RequestBody Usuario usuario);

    @GetMapping("/usuarios/search/buscar-username")
    public Usuario findByUsername(@RequestParam("nombre") String username);

    @GetMapping("/usuarios/search/buscar-dni-ruc")
    public Usuario findByDniRuc(@RequestParam("dniRuc") String dniRuc);

    @GetMapping("/usuarios/search/buscar-reset-password-token")
    public Usuario findByResetPasswordToken(@RequestParam("password") String password);
}
