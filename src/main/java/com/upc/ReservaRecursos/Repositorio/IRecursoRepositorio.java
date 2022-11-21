package com.upc.ReservaRecursos.Repositorio;

import com.upc.ReservaRecursos.Entidades.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecursoRepositorio extends JpaRepository<Recurso, Integer> {

}
