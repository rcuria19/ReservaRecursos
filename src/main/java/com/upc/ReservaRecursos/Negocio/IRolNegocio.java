package com.upc.ReservaRecursos.Negocio;


import com.upc.ReservaRecursos.Entidades.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolNegocio {

    public Page<Rol> listado(Pageable pageable);
    public Rol buscar(Integer id) throws Exception;

}
