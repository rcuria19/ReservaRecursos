package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Usuario;
import com.upc.ReservaRecursos.Entidades.UsuarioMain;
import com.upc.ReservaRecursos.Repositorio.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioNegocio implements IUsuarioNegocio{

    @Autowired
    IUsuarioRepositorio iUsuarioRepositorio;

    @Override
    public void registrar(Usuario usuario) throws Exception {
        iUsuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioRepositorio.findByUsuario(username).get();
        return UsuarioMain.build(usuario);
    }
}