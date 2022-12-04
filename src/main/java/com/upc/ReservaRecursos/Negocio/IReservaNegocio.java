package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Disponibilidad;
import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Entidades.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReservaNegocio {

    public void realizarReserva(Reserva reserva) throws  Exception;
    public void cancelarReserva(Integer id) throws  Exception;
    public void confirmarReserva(Integer id) throws  Exception;
    public Page<Reserva> consultaReservas(Pageable pageable);
    public Reserva obtenerReserva(Integer id) throws Exception;

}
