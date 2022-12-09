package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.Rol;
import com.upc.ReservaRecursos.Entidades.Usuario;
import com.upc.ReservaRecursos.Negocio.IRolNegocio;
import com.upc.ReservaRecursos.Negocio.IUsuarioNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class UsuarioRest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioNegocio usuarioNegocio;

    @Autowired
    private IRolNegocio rolNegocio;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/Usuario")
    public Page<Usuario> lista(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return usuarioNegocio.listado(pageable);
    }
    @GetMapping("/roles")
    public Page<Rol> listaRoles(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return rolNegocio.listado(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/Usuario")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario nuevoUsuario){
        Map<String, Object> response = new HashMap<>();
        try {
            Set<Rol> rolesUsuario = nuevoUsuario.getRoles();
            Set<Rol> roles = new HashSet<>();

            for (Rol r: rolesUsuario)
            {
                rolNegocio.buscar(r.getId());
                roles.add(r);
            }

            Usuario usuario = new Usuario(nuevoUsuario.getId(), nuevoUsuario.getUsuario(),
                    passwordEncoder.encode("123456"), nuevoUsuario.getNombre(), nuevoUsuario.getSexo(), nuevoUsuario.getFechaNacimiento());
            System.out.println(roles);
            System.out.println("roles...........................");
            usuario.setRoles(roles);
            usuarioNegocio.registrar(usuario);
            response.put("mensaje", "Usuario registrado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/Usuario")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Usuario nuevoUsuario){
        Map<String, Object> response = new HashMap<>();
        try {
            Set<Rol> rolesUsuario = nuevoUsuario.getRoles();
            Set<Rol> roles = new HashSet<>();

            for (Rol r: rolesUsuario)
            {
                rolNegocio.buscar(r.getId());
                roles.add(r);
            }

            Usuario usuario = new Usuario(nuevoUsuario.getId(), nuevoUsuario.getUsuario(), nuevoUsuario.getNombre(), nuevoUsuario.getSexo(), nuevoUsuario.getFechaNacimiento());
            usuario.setRoles(roles);
            usuarioNegocio.actualizar(usuario);
            response.put("mensaje", "Usuario actualizado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Usuario/{id_usuario}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id_usuario") Integer id){
        Usuario usuario;
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuarioNegocio.buscar(id);
            usuarioNegocio.eliminar(usuario);
            response.put("mensaje", "Usuario eliminado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            //logger.error("Error en registro", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }



}
