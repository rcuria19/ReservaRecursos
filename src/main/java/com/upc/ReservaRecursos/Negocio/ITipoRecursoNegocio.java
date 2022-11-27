package com.upc.ReservaRecursos.Negocio;
import com.upc.ReservaRecursos.Entidades.TipoRecurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITipoRecursoNegocio {
    public Page<TipoRecurso> listado(Pageable pageable);
    public TipoRecurso buscar(Integer id) throws Exception;
    public void registrar(TipoRecurso tipoRecurso)throws  Exception;
    public void actualizar(TipoRecurso tipoRecurso) throws  Exception;
    public void eliminar(TipoRecurso tipoRecurso) throws  Exception;
}
