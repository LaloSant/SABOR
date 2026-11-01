package com.flerchante.recetario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flerchante.recetario.model.entity.Ingrediente;
import com.flerchante.recetario.service.ingrediente.IngredienteServiceImpl;
import com.flerchante.recetario.service.usuario.UserUtils;

@Controller
public class IngredienteController {

	@Autowired
	private UserUtils userUtils;

	@Autowired
	private IngredienteServiceImpl ingredienteRepositorio;

	private static final String RUTA_VISTA = "vistas/ingredientes/";

	@GetMapping("/ingredientes")
	public String getListaIngredientes(Model modelo) {
		userUtils.setUserSession(modelo);
		List<Ingrediente> lista = ingredienteRepositorio.listarTodo();
		modelo.addAttribute("ingredientes", lista);
		return RUTA_VISTA + "lista";
	}

	@GetMapping("/ingredientes/id")
	public String getIngrediente(Model modelo, @RequestParam(required = true, defaultValue = "1") Long idIngrediente) {
		userUtils.setUserSession(modelo);
		Ingrediente i = ingredienteRepositorio.obtener(idIngrediente);
		modelo.addAttribute("idIngrediente", i.getIdIngrediente());
		modelo.addAttribute("nombreIngrediente", i.getNombre());
		modelo.addAttribute("costo", i.getCostoUnitario());
		modelo.addAttribute("utilizado", i.calcularVecesUsado());
		if (i.getPatrocinador() != null) {
			modelo.addAttribute("nombrePatrocinador", i.getPatrocinador().getNombre());
			modelo.addAttribute("link", i.getPatrocinador().getEnlace());
		} else {
			modelo.addAttribute("nombrePatrocinador", "Sin patrocinador oficial");
			modelo.addAttribute("link", "");
		}

		return RUTA_VISTA + "ver";
	}
}
