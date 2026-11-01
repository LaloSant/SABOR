package com.flerchante.recetario.service.detalle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flerchante.recetario.model.entity.Detalle;
import com.flerchante.recetario.model.entity.Ingrediente;
import com.flerchante.recetario.model.entity.Receta;
import com.flerchante.recetario.repository.DetalleRepository;
import com.flerchante.recetario.service.ingrediente.IngredienteServiceImpl;

import jakarta.transaction.Transactional;

@Service
public class DetalleServiceImpl implements DetalleService {

	@Autowired
	DetalleRepository repositorio;

	@Autowired
	IngredienteServiceImpl ingredienteRepositorio;

	@Override
	public List<Detalle> listarTodo() {
		return repositorio.findAll();
	}

	@Override
	public List<Detalle> buscarPorIngredientes(List<Long> ids) {
		return repositorio.findByIngredientes(ids);
	}
	
	@Override
	public List<Long> buscarPorIngredientes(List<Long> ids, Long size) {
		return repositorio.recetasPorIngredientes(ids, size);
	}

	@Override
	public List<Detalle> detallePorIdReceta(Long idReceta) {
		return repositorio.findByReceta_IdReceta(idReceta);
	}

	@Override
	public Detalle guardarDetalle(Detalle detalle) {
		if (detalle == null) {
			return null;
		}
		return repositorio.save(detalle);
	}

	@Override
	@Transactional
	public void actualizarDetalle(Long idDetalle, Double cantidad, Long idIngrediente) {
		repositorio.actualizarDetalle(idDetalle, cantidad, idIngrediente);
	}

	@Override
	public void guardarDetalles(List<Detalle> detalles, Receta receta) {
		if (detalles != null) {
			for (Detalle detalle : detalles) {
				Ingrediente ing = ingredienteRepositorio.obtener(detalle.getIngrediente().getIdIngrediente());
				//Ingrediente ing = detalle.getIngrediente();
				detalle.setReceta(receta);
				detalle.setCosto(ing.getCostoUnitario() * detalle.getCantidad());
				repositorio.save(detalle);
			}
		}
	}

	@Override
	public void eliminarDetalle(Detalle detalle) {
		if (detalle == null) {
			return;
		}
		repositorio.delete(detalle);
	}

	@Override
	public void actualizarDetalles() {
		List<Detalle> detalles = repositorio.findAll();
		for (Detalle detalle : detalles) {
			Ingrediente ing = detalle.getIngrediente();
			detalle.setCosto(ing.getCostoUnitario() * detalle.getCantidad());
			repositorio.save(detalle);
		}
	}

}
