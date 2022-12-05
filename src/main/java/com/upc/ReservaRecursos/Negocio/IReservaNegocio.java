package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReservaNegocio {

    public void realizarReserva(Reserva reserva) throws  Exception;
    public void cancelarReserva(Integer id) throws  Exception;
    public void confirmarReserva(Integer id) throws  Exception;
    public Page<Reserva> consultaReservas(Pageable pageable);
    public Page<Reserva>  consultarReservasUsuario(Integer idUsuario,Pageable pageable);
    public Reserva obtenerReserva(Integer id) throws Exception;

}
