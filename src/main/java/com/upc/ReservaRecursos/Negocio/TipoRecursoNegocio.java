package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.TipoRecurso;
import com.upc.ReservaRecursos.Repositorio.ITipoRecursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoRecursoNegocio implements ITipoRecursoNegocio{

    @Autowired
    ITipoRecursoRepositorio iTipoRecursoRepositorio;

    @Override
    public List<TipoRecurso> listado() {
        return iTipoRecursoRepositorio.findAll();
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
