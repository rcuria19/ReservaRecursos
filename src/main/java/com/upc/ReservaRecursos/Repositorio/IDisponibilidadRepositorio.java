package com.upc.ReservaRecursos.Repositorio;

import com.upc.ReservaRecursos.Entidades.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDisponibilidadRepositorio extends JpaRepository<Disponibilidad, Integer> {
}
