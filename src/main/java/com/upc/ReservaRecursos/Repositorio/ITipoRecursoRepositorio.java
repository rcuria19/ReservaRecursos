package com.upc.ReservaRecursos.Repositorio;

import com.upc.ReservaRecursos.Entidades.TipoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoRecursoRepositorio extends JpaRepository<TipoRecurso,Integer> {
}
