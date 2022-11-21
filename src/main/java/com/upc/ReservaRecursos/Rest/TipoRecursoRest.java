package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.TipoRecurso;
import com.upc.ReservaRecursos.Negocio.ITipoRecursoNegocio;
import com.upc.ReservaRecursos.Negocio.TipoRecursoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TipoRecursoRest {
    @Autowired
    private ITipoRecursoNegocio tipoRecursoNegocio;

    @GetMapping("/tiposRecurso")
    public List<TipoRecurso> lista(){
        return tipoRecursoNegocio.listado();
    }
}
