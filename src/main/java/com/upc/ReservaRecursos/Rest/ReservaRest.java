package com.upc.ReservaRecursos.Rest;

import com.upc.ReservaRecursos.Entidades.Recurso;
import com.upc.ReservaRecursos.Entidades.Reserva;
import com.upc.ReservaRecursos.Negocio.IReservaNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReservaRest {

    @Autowired
    private IReservaNegocio reservaNegocio;

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
            reservaNegocio.realizarReserva(reserva);
            response.put("mensaje", "Reserva registrada con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('ALUMN')")
    @PutMapping("/CancelarReserva/{id_reserva}")
    public ResponseEntity<?> cancelarReserva(@PathVariable(value = "id_reserva") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            reservaNegocio.cancelarReserva(id);
            response.put("mensaje", "Reserva cancelada con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('SECRETARY')")
    @PutMapping("/ConfirmarReserva/{id_reserva}")
    public ResponseEntity<?> confirmarReserva(@PathVariable(value = "id_reserva") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            reservaNegocio.confirmarReserva(id);
            response.put("mensaje", "Reserva confirmada con éxito!");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }




}
