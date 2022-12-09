package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Rol;
import com.upc.ReservaRecursos.Repositorio.IRolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class RolNegocio implements IRolNegocio{

    @Autowired
    IRolRepositorio iRolRepositorio;

    @Override
    public Page<Rol> listado(Pageable pageable) {
        return iRolRepositorio.findAll(pageable);
    }
    @Override
    public Rol buscar(Integer id) throws Exception {
        return iRolRepositorio.findById(id).orElseThrow(
                () -> new Exception("El rol no existe"));
    }


}
