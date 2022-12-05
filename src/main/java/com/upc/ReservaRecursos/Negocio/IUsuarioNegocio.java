package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface IUsuarioNegocio extends UserDetailsService {

    public void registrar(Usuario usuario)throws  Exception;

    public Usuario buscar(Integer id) throws Exception;

}
