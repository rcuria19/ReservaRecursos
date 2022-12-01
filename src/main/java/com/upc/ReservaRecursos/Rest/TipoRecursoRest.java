package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.TipoRecurso;
import com.upc.ReservaRecursos.Negocio.ITipoRecursoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TipoRecursoRest {
    @Autowired
    private ITipoRecursoNegocio tipoRecursoNegocio;

    @GetMapping("/tiposRecurso")
    public List<TipoRecurso> lista(){
        return tipoRecursoNegocio.listado();
    }

    @GetMapping("/tipoRecurso/{id_tipo_recurso}")
    public TipoRecurso buscar(@PathVariable(value = "id_tipo_recurso") Integer id){
        try {
            return tipoRecursoNegocio.buscar(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PostMapping("/TipoRecurso")
    public ResponseEntity<?> registrar(@RequestBody TipoRecurso tipoRecurso){
        Map<String, Object> response = new HashMap<>();
        try {
            tipoRecursoNegocio.registrar(tipoRecurso);
            response.put("mensaje", "Tipo de recurso registrado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PutMapping("/TipoRecurso")
    public ResponseEntity<?> actualizar(@RequestBody TipoRecurso tipoRecurso){
        Map<String, Object> response = new HashMap<>();
        try {
            tipoRecursoNegocio.actualizar(tipoRecurso);
            response.put("mensaje", "Tipo de recurso actualizado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @DeleteMapping("/TipoRecurso/{id_tipo_recurso}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id_tipo_recurso") Integer id){
        TipoRecurso tipoRecurso;
        Map<String, Object> response = new HashMap<>();
        try {
            tipoRecurso = tipoRecursoNegocio.buscar(id);
            tipoRecursoNegocio.eliminar(tipoRecurso);
            response.put("mensaje", "Tipo de recurso eliminado con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            //logger.error("Error en registro", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

}
