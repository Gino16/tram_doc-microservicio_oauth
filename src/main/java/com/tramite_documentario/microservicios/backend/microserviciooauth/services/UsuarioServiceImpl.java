package com.tramite_documentario.microservicios.backend.microserviciooauth.services;

import com.tramite_documentario.microservicios.backend.commonusuarios.models.entity.Usuario;
import com.tramite_documentario.microservicios.backend.microserviciooauth.clients.UsuarioFeignClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Override
    public Usuario findByUsername(String username) {
        return usuarioFeignClient.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);

        try {
            Usuario usuario = usuarioFeignClient.findByUsername(s);

            if (usuario == null) {
                logger.error("Error en login, no existe el usuaio '" + s + "' en el sistema");
                throw new UsernameNotFoundException("Error en login, no existe el usuaio '" + s + "' en el sistema");
            }

            List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> {
                return new SimpleGrantedAuthority(rol.getNombre());
            }).peek(simpleGrantedAuthority -> {
                logger.info("Rol: " + simpleGrantedAuthority.getAuthority());
            }).collect(Collectors.toList());

            logger.info("Usuario logeado: " + s);

            return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
        } catch (FeignException e) {
            String error = "Error en login, no existe el usuario '" + s + "' en el sistema";
            logger.error(error);
            throw new UsernameNotFoundException(error);
        }
    }
}
