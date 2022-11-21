package com.upc.ReservaRecursos.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "tb_recurso")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Integer id;
    @Column(name = "recurso")
    private String recurso;
    @Column(name = "descripcion_recurso")
    private String descripcion;
    @Column(name = "estado_recurso")
    private Boolean estado;
    @Column(name = "id_tipo_recurso")
    private Integer idTipoRecurso;

    public Recurso() {
    }

    public Recurso(Integer id, String recurso, String descripcion, Boolean estado, Integer idTipoRecurso) {
        this.id = id;
        this.recurso = recurso;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idTipoRecurso = idTipoRecurso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }
}
