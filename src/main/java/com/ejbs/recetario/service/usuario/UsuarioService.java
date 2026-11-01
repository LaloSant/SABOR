package com.ejbs.recetario.service.usuario;

import java.util.List;
import java.util.Optional;

import com.ejbs.recetario.model.entity.Usuario;

public interface UsuarioService {

    public List<Usuario> listarTodoUsuario();

    public Usuario guardarUsuario(Usuario usuario);

    public Optional<Usuario> obtenerUsuario(Long idUsuario);
    
    public Optional<Usuario> obtenerUsuario(String email);

    public Usuario actualizarUsuario(Usuario usuario);

    public void eliminarUsuario(Long idUsuario);

}
