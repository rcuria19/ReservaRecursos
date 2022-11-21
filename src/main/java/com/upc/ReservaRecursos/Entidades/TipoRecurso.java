package com.upc.ReservaRecursos.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "tb_tipo_recurso")
public class TipoRecurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_recurso")
    private Integer id;
    @Column(name = "tipo_recurso")
    private String tipoRecurso;
    @Column(name = "descripcion_tipo_recurso")
    private String descripcion;

    public TipoRecurso() {
    }

    public TipoRecurso(Integer id, String tipoRecurso, String descripcion) {
        this.id = id;
        this.tipoRecurso = tipoRecurso;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
