package com.upc.ReservaRecursos.Negocio;
import com.upc.ReservaRecursos.Entidades.TipoRecurso;

import com.upc.ReservaRecursos.Repositorio.ITipoRecursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoRecursoNegocio implements ITipoRecursoNegocio{

    @Autowired
    ITipoRecursoRepositorio iTipoRecursoRepositorio;

    @Override
    public Page<TipoRecurso> listado(Pageable pageable) {
        return iTipoRecursoRepositorio.findAll(pageable);
    }

    @Override
    public TipoRecurso buscar(Integer id) throws Exception {
        return iTipoRecursoRepositorio.findById(id).orElseThrow(
                () -> new Exception("El tipo de recurso ingresado no existe"));
    }

    @Override
    public void registrar(TipoRecurso tipoRecurso) throws Exception {
        iTipoRecursoRepositorio.save(tipoRecurso);
    }

    @Override
    public void actualizar(TipoRecurso tipoRecurso) throws Exception {
        buscar(tipoRecurso.getId());
        iTipoRecursoRepositorio.save(tipoRecurso);
    }

    @Override
    public void eliminar(TipoRecurso tipoRecurso) throws Exception {
        iTipoRecursoRepositorio.deleteById(tipoRecurso.getId());
    }
}
