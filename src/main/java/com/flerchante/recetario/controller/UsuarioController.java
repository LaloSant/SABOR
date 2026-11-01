package com.flerchante.recetario.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flerchante.recetario.model.entity.Usuario;
import com.flerchante.recetario.service.usuario.UserUtils;
import com.flerchante.recetario.service.usuario.UsuarioServiceImpl;


@Controller
public class UsuarioController {

	@Autowired
	private UserUtils userUtils;

	@Autowired
	private UsuarioServiceImpl usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String RUTA_VISTA = "vistas/usuarios/";

	@GetMapping("/usuarios")
	public String listarUsuarios(Model modelo) {
		modelo.addAttribute("usuarios", usuarioRepositorio.listarTodoUsuario());
		return RUTA_VISTA + "usuarios";
	}

	@GetMapping("/usuarios/editar")
	public String editarUsuario(Model modelo) {
		userUtils.setUserSession(modelo);
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user == null) {
			return "redirect:/recetas";
		}
		modelo.addAttribute("nombre_usuario",user.getNombre());
		modelo.addAttribute("correo_usuario",user.getEmail());


		return RUTA_VISTA + "editar";
	}

	@GetMapping("/usuarios/cambiar")
	public String cambiar(Model modelo, @RequestParam(required = false) String error, @RequestParam(required = false) String exito) {
		userUtils.setUserSession(modelo);
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user == null) {
			return "redirect:/recetas";
		}
		
		if (error != null) modelo.addAttribute("error", error);
		if (exito != null) modelo.addAttribute("exito", exito);
		
		modelo.addAttribute("nombre_usuario",user.getNombre());
		modelo.addAttribute("correo_usuario",user.getEmail());

		return RUTA_VISTA + "cambiar";
	}
	
	@PostMapping("/usuarios/cambiar/nombre")
	public String actualizarNombre(@RequestParam String nombre) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user != null && nombre != null && !nombre.isBlank()) {
			user.setNombre(nombre);
			usuarioRepositorio.guardarUsuario(user);
			return "redirect:/usuarios/cambiar?exito=Nombre actualizado correctamente";
		}
		return "redirect:/usuarios/cambiar?error=El nombre no puede estar vacio";
	}

	@PostMapping("/usuarios/cambiar/correo")
	public String actualizarCorreo(@RequestParam String email) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user != null && email != null && !email.isBlank()) {
			// Verificar si el correo ya existe en otro usuario
			Optional<Usuario> existente = usuarioRepositorio.obtenerUsuario(email);
			if (existente.isPresent() && !existente.get().getIdUsuario().equals(user.getIdUsuario())) {
				return "redirect:/usuarios/cambiar?error=El correo ya esta en uso por otro usuario";
			}
			user.setEmail(email);
			usuarioRepositorio.guardarUsuario(user);
			return "redirect:/usuarios/cambiar?exito=Correo actualizado correctamente";
		}
		return "redirect:/usuarios/cambiar?error=Correo invalido";
	}

	@PostMapping("/usuarios/cambiar/password")
	public String actualizarPassword(@RequestParam String newPassword, @RequestParam String oldPassword) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user != null) {
			if (!passwordEncoder.matches(oldPassword, user.getContrasenia())) {
				return "redirect:/usuarios/cambiar?error=La contraseña anterior no coincide";
			}
			if (newPassword == null || newPassword.isBlank()) {
				return "redirect:/usuarios/cambiar?error=La nueva contraseña no puede estar vacia";
			}
			user.setContrasenia(passwordEncoder.encode(newPassword));
			usuarioRepositorio.guardarUsuario(user);
			return "redirect:/usuarios/cambiar?exito=Contraseña actualizada correctamente";
		}
		return "redirect:/usuarios/cambiar";
	}

	// Endpoint para validación asíncrona (AJAX) desde la vista
	@PostMapping("/usuarios/validar-password")
	@ResponseBody
	public boolean validarPasswordAjax(@RequestParam String password) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user != null && password != null) {
			return passwordEncoder.matches(password, user.getContrasenia());
		}
		return false;
	}

}
