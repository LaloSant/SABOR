package com.flerchante.recetario.service.paso;

import java.sql.Blob;
import java.util.List;

import com.flerchante.recetario.model.entity.Paso;
import com.flerchante.recetario.model.entity.Receta;

public interface PasoService {
	public List<Paso> obtenerTodoPaso();
	
	public void actualizarPaso(Long idPaso, String notas);

	public void actualizarPaso(Long idPaso, String notas, Blob imagen);

	public void asignarPeticionIA(Long idPaso, Long idPeticionIA);

	public Paso obtenerPaso(Long idPaso);

	public void guardarPasos(List<Paso> pasos, Receta receta);

	public void guardarPasos(List<Paso> pasos, Receta receta, Long indicePaso);

	public void eliminarPaso(Paso paso);

	public void guardarPaso(Paso paso);
}
