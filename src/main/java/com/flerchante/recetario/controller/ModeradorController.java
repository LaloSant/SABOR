package com.flerchante.recetario.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.flerchante.recetario.model.dto.PeticionDTO;
import com.flerchante.recetario.model.entity.Paso;
import com.flerchante.recetario.model.entity.PeticionIA;
import com.flerchante.recetario.model.entity.Usuario;
import com.flerchante.recetario.service.paso.PasoServiceImpl;
import com.flerchante.recetario.service.peticiones.PeticionesServiceImpl;
import com.flerchante.recetario.service.usuario.UsuarioServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModeradorController {
	private static final String RUTA_MODERADOR = "vistas/moderador/";

	@Autowired
	private UsuarioServiceImpl usuarioRepositorio;

	@Autowired
	private PeticionesServiceImpl peticionesRepositorio;

	@Autowired
	private PasoServiceImpl pasoRepositorio;

	@GetMapping("/moderador/peticiones")
	public String verPeticiones(Model modelo, @RequestParam(required = false) String filtro) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user != null) {
			modelo.addAttribute("nomUser", user.getNombre());
			modelo.addAttribute("rol", user.getRol().getNombre());
			modelo.addAttribute("usuarioSesion", user);
		}
		List<PeticionIA> peticiones = peticionesRepositorio.obtenerTodaPeticion();
		String filtroText = "";
		if (filtro != null) {
			peticiones = peticiones.stream().filter(p -> p.getEstatus().equals(filtro)).collect(Collectors.toList());
			switch (filtro) {
				case "ENT" -> {
					filtroText = "ENTREGADOS";
				}
				case "PEN" -> {
					filtroText = "PENDIENTES";
				}
				case "DEN" -> {
					filtroText = "DENEGADOS";
				}
			}
			modelo.addAttribute("peticiones", peticiones);
		}
		modelo.addAttribute("filtro", filtroText);
		modelo.addAttribute("peticiones", peticiones);
		return RUTA_MODERADOR + "peticiones";
	}

	@GetMapping("/moderador/peticiones/ver{idPeticionIA}")
	public String verPeticiones(Model modelo, @RequestParam Long idPeticionIA) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user == null || user.getRol().getIdRol().equals("USER")) {
			return "redirect:/recetas";
		}
		if (user != null) {
			modelo.addAttribute("nomUser", user.getNombre());
			modelo.addAttribute("rol", user.getRol().getNombre());
			modelo.addAttribute("usuarioSesion", user);
		}
		PeticionIA peticion = peticionesRepositorio.obtenerPeticion(idPeticionIA);
		PeticionDTO dto = new PeticionDTO(peticion, null);
		modelo.addAttribute("dto", dto);
		return RUTA_MODERADOR + "verPeticion";
	}

	@PostMapping("/peticion/modificar")
	public String modificarPeticion(@ModelAttribute PeticionDTO dto) {
		PeticionIA pet = peticionesRepositorio.obtenerPeticion(dto.getPeticion().getIdPeticionIA());
		peticionesRepositorio.actualizarPeticion(pet.getIdPeticionIA(), "ENT");
		Paso paso = pet.getPaso();
		pasoRepositorio.actualizarPaso(paso.getIdPaso(), paso.getNotas(), ImagenController.mpfTBlob(dto.getMpf()));
		return "redirect:/moderador/peticiones";
	}

	@GetMapping("/moderador/peticiones/denegar{idPeticionIA}")
	public String getMethodName(Model model, @RequestParam Long idPeticionIA) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user == null || user.getRol().getIdRol().equals("USER")) {
			return "redirect:/recetas";
		}
		PeticionIA pet = peticionesRepositorio.obtenerPeticion(idPeticionIA);
		peticionesRepositorio.actualizarPeticion(pet.getIdPeticionIA(), "DEN");
		return "redirect:/moderador/peticiones";
	}

}
