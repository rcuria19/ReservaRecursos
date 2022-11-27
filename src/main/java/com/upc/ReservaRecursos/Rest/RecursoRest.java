package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Negocio.IRecursoNegocio;
import com.upc.ReservaRecursos.Negocio.ITipoRecursoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecursoRest {
    @Autowired
    private IRecursoNegocio recursoNegocio;

    @Autowired
    private ITipoRecursoNegocio tipoRecursoNegocio;

    @GetMapping("/recursos")
    public Page<Recurso> lista(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return recursoNegocio.listado(pageable);
    }

    @GetMapping("/recursos/{id_recurso}")
    public Recurso buscar(@PathVariable(value = "id_recurso") Integer id){
        try {
            return recursoNegocio.buscar(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PostMapping("/recursos")
    public ResponseEntity<?> registrar(@RequestBody Recurso recurso){
        Map<String, Object> response = new HashMap<>();
        try {
            tipoRecursoNegocio.buscar(recurso.getIdTipoRecurso());
            recursoNegocio.registrar(recurso);
            response.put("mensaje", "Recurso registrado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PutMapping("/recursos")
    public ResponseEntity<?> actualizar(@RequestBody Recurso recurso){
        Map<String, Object> response = new HashMap<>();
        try {
            tipoRecursoNegocio.buscar(recurso.getIdTipoRecurso());
            recursoNegocio.actualizar(recurso);
            response.put("mensaje", "Recurso actualizado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @DeleteMapping("/recursos/{id_recurso}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id_recurso") Integer id){
        Recurso recurso;
        Map<String, Object> response = new HashMap<>();
        try {
            recurso = recursoNegocio.buscar(id);
            recursoNegocio.eliminar(recurso);
            response.put("mensaje", "Recurso eliminado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            //logger.error("Error en registro", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

}
