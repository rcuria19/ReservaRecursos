package com.upc.ReservaRecursos.Negocio;

import com.upc.ReservaRecursos.Entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUsuarioNegocio extends UserDetailsService {

    public Usuario buscar(Integer id) throws Exception;
    public void registrar(Usuario usuario)throws  Exception;
    public void actualizar(Usuario usuario) throws  Exception;
    public void eliminar(Usuario usuario) throws  Exception;

}
