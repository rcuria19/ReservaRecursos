package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Reserva;
import com.upc.ReservaRecursos.Repositorio.IReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaNegocio implements IReservaNegocio{

    @Autowired
    IReservaRepositorio iReservaRepositorio;

    @Override
    public void realizarReserva(Reserva reserva) throws Exception {
        iReservaRepositorio.save(reserva);
    }

    @Override
    public void cancelarReserva(Integer id) throws Exception {
        Reserva reserva = obtenerReserva(id);
        reserva.setEstado(2);
        iReservaRepositorio.save(reserva);
    }

    @Override
    public void confirmarReserva(Integer id) throws Exception {
        Reserva reserva = obtenerReserva(id);
        reserva.setEstado(3);
        iReservaRepositorio.save(reserva);
    }

    @Override
    public Page<Reserva> consultaReservas(Pageable pageable) {
        return iReservaRepositorio.findByEstado(1, pageable);
    }

    @Override
    public Page<Reserva> consultarReservasUsuario(Integer idUsuario,Pageable pageable) {
        return iReservaRepositorio.findByIdUsuario(idUsuario, pageable);
    }

    @Override
    public Reserva obtenerReserva(Integer id) throws Exception {
        return iReservaRepositorio.findById(id).orElseThrow(
                () -> new Exception("La reserva no existe"));
    }

}
