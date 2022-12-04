package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Disponibilidad;
import com.upc.ReservaRecursos.Repositorio.IDisponibilidadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DisponibilidadNegocio implements IDisponibilidadNegocio{

    @Autowired
    IDisponibilidadRepositorio iDisponibilidadRepositorio;

    @Override
    public Page<Disponibilidad> listado(Pageable pageable) {
        return iDisponibilidadRepositorio.findAll(pageable);
    }
    @Override
    public Disponibilidad buscar(Integer id) throws Exception {
        return iDisponibilidadRepositorio.findById(id).orElseThrow(
                () -> new Exception("El día indicado no existe"));
    }

    @Override
    public void actualizar(Disponibilidad disponibilidad) throws Exception {
        buscar(disponibilidad.getId());
        iDisponibilidadRepositorio.save(disponibilidad);
    }

}
