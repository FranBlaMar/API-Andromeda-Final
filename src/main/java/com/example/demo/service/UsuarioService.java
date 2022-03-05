package com.example.demo.service;


import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

/**
 * Servicio donde gestionamos la lógica de negocio de usuarios
 * @author usuario
 *
 */
@Component
public class UsuarioService implements UserDetailsService {

    @Autowired private UserRepository repositorio;

   
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userRes = repositorio.findById(userName);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("No se pudo encontrar un usuario con el nombre de usuario " + userName);
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                userName,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
    
    /**
     * Metodo para obtener un usuario desde su nombre de usuario
     * @param userName nombre de usuario buscado
     * @return Usuario buscado
     */
    public User getUserbyUserName( String userName) {
    	return this.repositorio.findById(userName).get();
    }
    /**
     * Metodo para modificar un usuario
     * @param usuarioModificado
     * @return El usuario modificado
     */
    public User editUsuario(User usuarioModificado) {
    	User usuario = this.repositorio.findById(usuarioModificado.getUserName()).get();
    	usuarioModificado.setPassword(usuario.getPassword());
    	return this.repositorio.save(usuarioModificado);
    }
}
