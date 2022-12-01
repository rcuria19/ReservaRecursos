package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.TipoRecurso;

import java.util.List;

public interface ITipoRecursoNegocio {
    public List<TipoRecurso> listado();
    public TipoRecurso buscar(Integer id) throws Exception;
    public void registrar(TipoRecurso tipoRecurso)throws  Exception;
    public void actualizar(TipoRecurso tipoRecurso) throws  Exception;
    public void eliminar(TipoRecurso tipoRecurso) throws  Exception;
}
