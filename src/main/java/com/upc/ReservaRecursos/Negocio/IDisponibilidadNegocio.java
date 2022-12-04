package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Disponibilidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IDisponibilidadNegocio {

    public Page<Disponibilidad> listado(Pageable pageable);
    public Disponibilidad buscar(Integer id) throws Exception;
    public void actualizar(Disponibilidad disponibilidad) throws  Exception;

}
