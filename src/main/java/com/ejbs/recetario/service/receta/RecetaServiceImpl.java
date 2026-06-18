package com.ejbs.recetario.service.receta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejbs.recetario.model.entity.Receta;
import com.ejbs.recetario.repository.RecetaRepository;

@Service
public class RecetaServiceImpl implements RecetaService {

	@Autowired
	private RecetaRepository repositorio;

	@Override
	public List<Receta> listarTodaReceta() {
		return repositorio.findAll();
	}

	@Override
	public Receta guardarReceta(Receta receta) {
		receta.setVisitasSemanales(0l);
		receta.setVisitasTotales(0l);
		receta.setCalificacion(0d);
		receta.setCalificacionesTotales(0l);
		return repositorio.save(receta);
	}

	@Override
	public Optional<Receta> obtenerRecetaPorID(Long idReceta) {
		if (idReceta == null) {
			return null;
		}
		return repositorio.findById(idReceta);
	}

	@Override
	public List<Receta> obtenerTodoPor(String nombre) {
		return repositorio.findBynombreIgnoreCaseContaining(nombre);
	}

	@Override
	public List<Receta> obtenerTodoPor(List<Long> ids) {
		if (ids == null) {
			return null;
		}
		return repositorio.findAllById(ids);
	}

	@Override
	public List<Receta> obtenerTopSemana() {
		return repositorio.findAllByOrderByVisitasSemanalesDesc();
	}

	@Override
	public List<Receta> obtenerTopTotal() {
		return repositorio.findAllByOrderByVisitasTotalesDesc();
	}

	@Override
	public Optional<Receta> obtenerRecetasTotal() {
		throw new UnsupportedOperationException("Unimplemented method 'obtenerRecetasTotal'");
	}

	@Override
	public Receta actualizarReceta(Receta receta) {
		if (receta == null) {
			return null;
		}
		return repositorio.save(receta);
	}

	@Override
	public void eliminarReceta(Long idReceta) {
		if (idReceta == null) {
			return;
		}
		repositorio.deleteById(idReceta);
	}

	@Override
	@Transactional
	public void aumentarVisita(Long idReceta) {
		repositorio.aumentarVisita(idReceta);

	}

	@Override
	@Transactional
	public void actualizarNombre(Long idReceta, String nombre) {
		repositorio.actualizarNombre(idReceta, nombre);
	}

	@Override
	@Transactional
	public void actualizarCalificacion(Long idReceta, Double calificacion) {
		repositorio.actualizarCalificacion(idReceta, calificacion);
	}

}
