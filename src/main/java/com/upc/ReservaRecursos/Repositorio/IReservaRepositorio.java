package com.upc.ReservaRecursos.Repositorio;

import com.upc.ReservaRecursos.Entidades.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservaRepositorio extends JpaRepository<Reserva, Integer> {

    Page<Reserva> findByEstado(Integer estado, Pageable pageable);


}
