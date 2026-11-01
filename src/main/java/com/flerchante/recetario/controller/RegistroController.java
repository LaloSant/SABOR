package com.flerchante.recetario.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flerchante.recetario.model.entity.Rol;
import com.flerchante.recetario.model.entity.Usuario;
import com.flerchante.recetario.service.rol.RolServiceImpl;
import com.flerchante.recetario.service.usuario.UserUtils;
import com.flerchante.recetario.service.usuario.UsuarioServiceImpl;

@Controller
public class RegistroController {

	@Autowired
	private UserUtils userUtils;

	@Autowired
	private UsuarioServiceImpl repositorioUsuario;

	@Autowired
	private RolServiceImpl repositorioRol;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/registro")
	public String registro(Model model, @RequestParam(required = false) String error) {
		if (error != null && "1".equals(error)) {
			model.addAttribute("error", error);
		}
		model.addAttribute("usuario", new Usuario());
		userUtils.setUserSession(model);
		return "registro";
	}

	@PostMapping(value = "/registro")
	public String guardarUsuario(@ModelAttribute Usuario usuario) {
		Optional<Usuario> usuarioExistente = repositorioUsuario.obtenerUsuario(usuario.getEmail());
		if (usuarioExistente.isPresent()) {
			return "redirect:/registro?error=1";
		}
		Optional<Rol> rolOptional = repositorioRol.obtenerRolPorID("USER");
		if (!rolOptional.isPresent()) {
			usuario.setRol(null);
			return "login";
		}
		usuario.setRol(rolOptional.get());
		usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
		repositorioUsuario.guardarUsuario(usuario);
		return "login";
	}

}
