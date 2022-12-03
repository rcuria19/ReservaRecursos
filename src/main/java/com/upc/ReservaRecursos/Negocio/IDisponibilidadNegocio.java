package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Disponibilidad;


public interface IDisponibilidadNegocio {

    public Disponibilidad buscar(Integer id) throws Exception;
    public void actualizar(Disponibilidad disponibilidad) throws  Exception;

}
