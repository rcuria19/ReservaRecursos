package com.upc.ReservaRecursos.Repositorio;

import com.upc.ReservaRecursos.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsuario(String nombreUsuario);
}
