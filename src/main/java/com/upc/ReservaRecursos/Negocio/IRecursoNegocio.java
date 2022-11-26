package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Entidades.TipoRecurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRecursoNegocio {
    public Page<Recurso> listado(Pageable pageable);
    public void registrar(Recurso recurso)throws  Exception;
    public Recurso buscar(Integer id) throws Exception;
    public void actualizar(Recurso recurso) throws  Exception;
    public void eliminar(Recurso recurso) throws  Exception;
}
