package com.flerchante.recetario.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flerchante.recetario.model.entity.Detalle;
import com.flerchante.recetario.model.entity.Ingrediente;
import com.flerchante.recetario.model.entity.Patrocinador;
import com.flerchante.recetario.model.entity.Receta;
import com.flerchante.recetario.service.patrocinador.PatrocinadorServiceImpl;
import com.flerchante.recetario.service.usuario.UserUtils;

@Controller
public class PatrocinadorController {

	@Autowired
	private PatrocinadorServiceImpl patrocinadorRepositorio;

	@Autowired
	private UserUtils userUtils;

	private static final String RUTA_VISTA = "vistas/patrocinadores/";

	@GetMapping("/patrocinadores")
	public String getListaIngredientes(Model modelo) {
		userUtils.setUserSession(modelo);

		List<Patrocinador> listaPat = patrocinadorRepositorio.listar();
		HashMap<Patrocinador, List<Receta>> relacionPR = new HashMap<>();
		for (Patrocinador patrocinador : listaPat) {
			List<Receta> listaRec = new ArrayList<>();
			Set<Long> idsRecetas = new HashSet<>();
			for (Ingrediente ing : patrocinador.getIngredientes()) {
				for (Detalle detalle : ing.getDetalles()) {
					Receta receta = detalle.getReceta();
					if (receta != null && receta.getIdReceta() != null && idsRecetas.add(receta.getIdReceta())) {
						listaRec.add(receta);
					}
				}
			}
			relacionPR.put(patrocinador, listaRec);
		}
		modelo.addAttribute("Mapa", relacionPR);
		return RUTA_VISTA + "lista";
	}

}
