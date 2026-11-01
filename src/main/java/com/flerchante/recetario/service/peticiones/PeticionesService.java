package com.flerchante.recetario.service.peticiones;

import java.util.List;

import com.flerchante.recetario.model.entity.PeticionIA;

public interface PeticionesService {
	public PeticionIA generarPeticion(String descripcion);
	
	public PeticionIA obtenerPeticion(Long idPeticion);

	public List<PeticionIA> obtenerTodaPeticion();

	public void actualizarPeticion(Long idPeticion, String estatus);

	public void eliminarPeticion(Long idPeticion);

	public List<PeticionIA> obtenerPeticionesPorEstatus(String estatus);


}
