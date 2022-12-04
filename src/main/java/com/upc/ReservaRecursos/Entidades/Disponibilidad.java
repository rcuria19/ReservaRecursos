package com.upc.ReservaRecursos.Entidades;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "tb_disponibilidad")
public class Disponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Integer id;

    @Column(name = "hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fin")
    private Time horaFin;

    @Column(name = "dia")
    private Integer dia;

    public Disponibilidad() {
    }

    public Disponibilidad(Integer id, Time horaInicio, Time horaFin, Integer dia) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }
}
