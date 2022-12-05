package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Repositorio.IRecursoRepositorio;
import com.upc.ReservaRecursos.Repositorio.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class RecursoNegocio implements IRecursoNegocio{

    @Autowired
    IRecursoRepositorio iRecursoRepositorio;

    @Override
    public Page<Recurso> listado(Pageable pageable) {
        return iRecursoRepositorio.findAll(pageable);
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
