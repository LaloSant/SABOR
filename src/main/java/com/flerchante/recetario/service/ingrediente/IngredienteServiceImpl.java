package com.flerchante.recetario.service.ingrediente;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flerchante.recetario.model.entity.Ingrediente;
import com.flerchante.recetario.repository.IngredienteRepository;

@Service
public class IngredienteServiceImpl implements IngredienteService {

	@Autowired
	IngredienteRepository repositorio;

	@Override
	public Ingrediente obtener(Long idIngrediente) {
		if (idIngrediente == null) {
			return null;
		}
		return repositorio.findById(idIngrediente).get();
	}

	@Override
	public List<Ingrediente> listarTodo() {
		return repositorio.findAll().stream()
				.sorted(Comparator.comparing(Ingrediente::getNombre))
				.toList();
	}

	@Override
	public Ingrediente guardar(Ingrediente ingrediente) {
		if (ingrediente == null) {
			return null;
		}
		return repositorio.save(ingrediente);
	}

	// @Override
	// public Ingrediente actualizar(Ingrediente ingrediente) {
	// 	return this.guardar(ingrediente);
	// }

}
