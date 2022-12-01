package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.Usuario;
import com.upc.ReservaRecursos.Negocio.IUsuarioNegocio;
import com.upc.ReservaRecursos.Seguridad.DTO.JwtDto;
import com.upc.ReservaRecursos.Seguridad.DTO.Mensaje;
import com.upc.ReservaRecursos.Seguridad.DTO.UsuarioLogin;
import com.upc.ReservaRecursos.Seguridad.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UsuarioRest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
/*
    @Autowired
    RolService rolService;*/

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    private IUsuarioNegocio usuarioNegocio;

    @PostMapping("/NuevoUsuario")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario nuevoUsuario){
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = new Usuario(nuevoUsuario.getId(), nuevoUsuario.getUsuario(),
                    passwordEncoder.encode(nuevoUsuario.getContraseña()), nuevoUsuario.getNombre());
            usuarioNegocio.registrar(usuario);
            response.put("mensaje", "Usuario registrado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UsuarioLogin usuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(),
                                usuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }



}
