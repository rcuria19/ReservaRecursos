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

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthRest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioNegocio usuarioNegocio;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UsuarioLogin usuario, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(),
                                usuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Usuario user = usuarioNegocio.buscarPorUsername(userDetails.getUsername());

        String jwt = jwtProvider.generateToken(authentication, user);
        System.out.println(authentication);

        System.out.println("-------------------------------------------");



        System.out.println(user.getId());
        System.out.println("-------------------------------------------");

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }


}
