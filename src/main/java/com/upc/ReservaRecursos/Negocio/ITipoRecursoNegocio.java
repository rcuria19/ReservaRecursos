package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.TipoRecurso;

import java.util.List;

public interface ITipoRecursoNegocio {
    public List<TipoRecurso> listado();
    public TipoRecurso buscar(Integer id) throws Exception;
}
