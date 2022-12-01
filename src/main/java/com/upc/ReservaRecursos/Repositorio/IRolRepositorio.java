package com.upc.ReservaRecursos.Repositorio;

import com.upc.ReservaRecursos.Entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepositorio extends JpaRepository<Rol, Integer> {

}
