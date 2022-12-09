package com.upc.ReservaRecursos.Entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contraseña")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "sexo")
    private String sexo;


    @ManyToMany(fetch = FetchType.EAGER)
    // join columns hace referencia a la columna que hace referencia hacia esta
    // Es decir la tabla usuario_rol va a tener un campo que se llama id_usuario
    // inverseJoinColumns = el inverso, hace referencia a rol
    @JoinTable(name = "tb_usuario_rol", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Integer id, String usuario, String password, String nombre, String sexo, Date fechaNacimiento) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario(Integer id, String usuario, String nombre, String sexo, Date fechaNacimiento) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                ", roles=" + roles +
                '}';
    }
}