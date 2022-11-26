package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Repositorio.IRecursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoNegocio implements IRecursoNegocio{

    @Autowired
    IRecursoRepositorio iRecursoRepositorio;

    @Override
    public List<Recurso> listado() {
        return iRecursoRepositorio.findAll();
    }

    @Override
    public void registrar(Recurso recurso) throws Exception {
        iRecursoRepositorio.save(recurso);
    }

    @Override
    public Recurso buscar(Integer id) throws Exception {
        return iRecursoRepositorio.findById(id).orElseThrow(
                () -> new Exception("El recurso no existe"));
    }

    @Override
    public void actualizar(Recurso recurso) throws Exception {
        buscar(recurso.getId());
        iRecursoRepositorio.save(recurso);
    }

    @Override
    public void eliminar(Recurso recurso) throws Exception {
        iRecursoRepositorio.deleteById(recurso.getId());
    }


}
