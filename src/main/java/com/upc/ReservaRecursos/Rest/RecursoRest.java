package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Negocio.IRecursoNegocio;
import com.upc.ReservaRecursos.Negocio.ITipoRecursoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecursoRest {
    @Autowired
    private IRecursoNegocio recursoNegocio;

    @Autowired
    private ITipoRecursoNegocio tipoRecursoNegocio;

    @GetMapping("/recursos")
    public List<Recurso> lista(){
        return recursoNegocio.listado();
    }

    @PostMapping("/registrarRecurso")
    public Recurso registrar(@RequestBody Recurso recurso){
        Recurso p;
        try {
            tipoRecursoNegocio.buscar(recurso.getIdTipoRecurso());
            p = recursoNegocio.registrar(recurso);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
        return p;
    }

    @PostMapping("/actualizarRecurso")
    public Recurso actualizar(@RequestBody Recurso recurso){
        Recurso p;
        try {
            tipoRecursoNegocio.buscar(recurso.getIdTipoRecurso());
            recursoNegocio.buscar(recurso.getId());
            p = recursoNegocio.actualizar(recurso);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
        return p;
    }

    @PostMapping("/deshabilitarRecurso")
    public Recurso deshabilitar(@RequestBody Recurso recurso){
        Recurso p;
        try {
            tipoRecursoNegocio.buscar(recurso.getIdTipoRecurso());
            recursoNegocio.buscar(recurso.getId());
            recurso.setEstado(false);
            p = recursoNegocio.deshabilitar(recurso);
        }catch (Exception e){
            //logger.error("Error en registro", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
        return p;
    }

}
