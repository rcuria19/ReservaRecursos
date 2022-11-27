package com.upc.ReservaRecursos.Rest;
import com.upc.ReservaRecursos.Entidades.TipoRecurso;
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
public class TipoRecursoRest {
    @Autowired
    private ITipoRecursoNegocio tipoRecursoNegocio;

    @GetMapping("/tiposRecurso")
    public Page<TipoRecurso> lista(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return tipoRecursoNegocio.listado(pageable);
    }

    @GetMapping("/tiposRecurso/{id_tipo_recurso}")
    public TipoRecurso buscar(@PathVariable(value = "id_tipo_recurso") Integer id){
        try {
            return tipoRecursoNegocio.buscar(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PostMapping("/tiposRecurso")
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

    @PutMapping("/tiposRecurso")
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

    @DeleteMapping("/tiposRecurso/{id_tipo_recurso}")
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
