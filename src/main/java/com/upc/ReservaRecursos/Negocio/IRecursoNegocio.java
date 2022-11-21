package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Entidades.TipoRecurso;

import java.util.List;

public interface IRecursoNegocio {
    public List<Recurso> listado();
    public Recurso registrar(Recurso recurso)throws  Exception;
    public Recurso buscar(Integer id) throws Exception;
    public Recurso actualizar(Recurso recurso) throws  Exception;
    public Recurso deshabilitar(Recurso recurso) throws  Exception;
}
