package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.Disponibilidad;
import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Negocio.IDisponibilidadNegocio;
import com.upc.ReservaRecursos.Negocio.IRecursoNegocio;
import com.upc.ReservaRecursos.Negocio.ITipoRecursoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DisponibilidadRest {

    @Autowired
    private IDisponibilidadNegocio disponibilidadNegocio;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/disponibilidad")
    public ResponseEntity<?> actualizar(@RequestBody Disponibilidad disponibilidad){
        Map<String, Object> response = new HashMap<>();
        try {
            disponibilidadNegocio.buscar(disponibilidad.getId());
            disponibilidadNegocio.actualizar(disponibilidad);
            response.put("mensaje", "disponibilidad actualizada con éxito!");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }



}
