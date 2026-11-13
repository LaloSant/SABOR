package com.ejbs.recetario.service.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ejbs.recetario.model.entity.Usuario;
import com.ejbs.recetario.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    @Override
    public List<Usuario> listarTodoUsuario() {
        return (List<Usuario>) repositorio.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
        return repositorio.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuario(Long idUsuario) {
		if (idUsuario == null) {
			return null;
		}
        return repositorio.findById(idUsuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuario(String email) {
        return repositorio.findByEmail(email);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
        return repositorio.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
		if (idUsuario == null) {
			return;
		}
        repositorio.deleteById(idUsuario);
    }

	public Usuario getUsuarioSesion() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<Usuario> optional = obtenerUsuario(auth.getName());
		return (optional.isPresent()) ? optional.get() : null;
	}

}
