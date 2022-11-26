package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Entidades.TipoRecurso;

import java.util.List;

public interface IRecursoNegocio {
    public List<Recurso> listado();
    public void registrar(Recurso recurso)throws  Exception;
    public Recurso buscar(Integer id) throws Exception;
    public void actualizar(Recurso recurso) throws  Exception;
    public void eliminar(Recurso recurso) throws  Exception;
}
