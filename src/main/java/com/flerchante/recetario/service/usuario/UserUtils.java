package com.flerchante.recetario.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.flerchante.recetario.model.entity.Usuario;

@Service
public class UserUtils {

	@Autowired
	private UsuarioServiceImpl usuarioRepositorio;

	public void setUserSession(Model model, Usuario user) {
		if (user != null) {
			model.addAttribute("nomUser", user.getNombre());
			model.addAttribute("rol", user.getRol().getNombre());
			model.addAttribute("usuarioSesion", user);
		}
	}
	
	public void setUserSession(Model model) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		setUserSession(model, user);
	}
}
