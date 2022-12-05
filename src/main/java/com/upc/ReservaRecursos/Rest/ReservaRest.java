package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Entidades.Reserva;
import com.upc.ReservaRecursos.Negocio.IRecursoNegocio;
import com.upc.ReservaRecursos.Negocio.IReservaNegocio;
import com.upc.ReservaRecursos.Negocio.IUsuarioNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReservaRest {

    @Autowired
    private IReservaNegocio reservaNegocio;

    @Autowired
    private IUsuarioNegocio usuarioNegocio;

    @Autowired
    private IRecursoNegocio recursoNegocio;

    @GetMapping("/reservas")
    public Page<Reserva> listarReservas(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return reservaNegocio.consultaReservas(pageable);
    }

    @GetMapping("/reservas/{id_reserva}")
    public Reserva buscar(@PathVariable(value = "id_reserva") Integer id){
        try {
            return reservaNegocio.obtenerReserva(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('ALUMN')")
    @PostMapping("/reservas")
    public ResponseEntity<?> realizarReserva(@RequestBody Reserva reserva){
        Map<String, Object> response = new HashMap<>();
        try {
            //Se valida si existe el usuario
            usuarioNegocio.buscar(reserva.getIdUsuario());
            //Se valida si existe recurso
            recursoNegocio.buscar(reserva.getIdRecurso());
            //Se valida que se reserve maximo 1 hora
            Integer horas = reserva.getHoraFin().getHours() - reserva.getHoraInicio().getHours();
            if(horas!=1)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Solo se puede reservar una hora ",new Exception("Solo se puede reservar una hora"));
            }
            //Se realiza la reserva
            reservaNegocio.realizarReserva(reserva);
            response.put("mensaje", "Reserva registrada con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('ALUMN')")
    @PutMapping("/CancelarReserva/{id_reserva}")
    public ResponseEntity<?> cancelarReserva(@PathVariable(value = "id_reserva") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            //Se valida si la reserva existe
            reservaNegocio.obtenerReserva(id);
            //Se valida si la reserva se encuentra cancelada o confirmada
            Reserva reserva = reservaNegocio.obtenerReserva(id);
            if (reserva.getEstado() == 2)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La reserva se encuentra cancelada",new Exception("La reserva se encuentra cancelada"));
            } else if (reserva.getEstado() == 3)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede cancelar una reserva confirmada",new Exception("No se puede cancelar una reserva confirmada"));
            }
            //Se solicita la cancelación de la reserva
            reservaNegocio.cancelarReserva(id);
            response.put("mensaje", "Reserva cancelada con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('SECRETARY')")
    @PutMapping("/ConfirmarReserva/{id_reserva}")
    public ResponseEntity<?> confirmarReserva(@PathVariable(value = "id_reserva") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            //Se valida si la reserva existe
            reservaNegocio.obtenerReserva(id);
            //Se valida si la reserva se encuentra cancelada o confirmada
            Reserva reserva = reservaNegocio.obtenerReserva(id);
            if (reserva.getEstado() == 3)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La reserva se encuentra confirmada",new Exception("La reserva se encuentra confirmada"));
            } else if (reserva.getEstado() == 2)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede cancelar una reserva cancelada",new Exception("No se puede cancelar una reserva cancelada"));
            }
            reservaNegocio.confirmarReserva(id);
            response.put("mensaje", "Reserva confirmada con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
    }




}
