package com.flerchante.recetario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flerchante.recetario.model.entity.Usuario;
import com.flerchante.recetario.service.usuario.UserUtils;

@Controller
public class LoginController {

	@Autowired
	private UserUtils userUtils;

	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error,
			@RequestParam(required = false) String logout) {

		userUtils.setUserSession(model);
		boolean reintento = (error != null);
		boolean logoutExito = (logout != null);
		model.addAttribute("reintento", reintento);
		model.addAttribute("logout", logoutExito);
		model.addAttribute("usuario", new Usuario());
		return "login";
	}

}
