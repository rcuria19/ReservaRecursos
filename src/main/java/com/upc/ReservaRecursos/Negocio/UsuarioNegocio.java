package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Usuario;
import com.upc.ReservaRecursos.Entidades.UsuarioMain;
import com.upc.ReservaRecursos.Repositorio.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioNegocio implements IUsuarioNegocio{

    @Autowired
    IUsuarioRepositorio iUsuarioRepositorio;

    @Override
    public Page<Usuario> listado(Pageable pageable) {
        return iUsuarioRepositorio.findAll(pageable);
    }

    @Override
    public Usuario buscar(Integer id) throws Exception {
        return iUsuarioRepositorio.findById(id).orElseThrow(
                () -> new Exception("El usuario no existe"));
    }

    @Override
    public void registrar(Usuario usuario) throws Exception {
        iUsuarioRepositorio.save(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) throws Exception {
        buscar(usuario.getId());
        iUsuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) throws Exception {
        iUsuarioRepositorio.deleteById(usuario.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioRepositorio.findByUsuario(username).get();
        return UsuarioMain.build(usuario);
    }
}
