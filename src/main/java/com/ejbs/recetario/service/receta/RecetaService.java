package com.ejbs.recetario.service.receta;

import java.util.List;
import java.util.Optional;

import com.ejbs.recetario.model.entity.Receta;

public interface RecetaService {

	public List<Receta> listarTodaReceta();

	public Receta guardarReceta(Receta receta);

	public Optional<Receta> obtenerRecetaPorID(Long idReceta);

	public List<Receta> obtenerTodoPor(String nombre);

	public List<Receta> obtenerTodoPor(List<Long> ids);

	public List<Receta> obtenerTopSemana();

	public List<Receta> obtenerTopTotal();

	public Optional<Receta> obtenerRecetasTotal();

	public Receta actualizarReceta(Receta receta);

	public void eliminarReceta(Long idReceta);

	public void aumentarVisita(Long idReceta);

	public void actualizarNombre(Long idReceta, String nombre);

	public void actualizarCalificacion(Long idReceta, Double calificacion);
}
