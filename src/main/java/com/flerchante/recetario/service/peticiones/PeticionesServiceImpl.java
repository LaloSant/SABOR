package com.flerchante.recetario.service.peticiones;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flerchante.recetario.model.entity.PeticionIA;
import com.flerchante.recetario.repository.PeticionesRepository;

import jakarta.transaction.Transactional;

@Service
public class PeticionesServiceImpl implements PeticionesService {

	@Autowired
	PeticionesRepository repositorio;

	@Override
	public PeticionIA obtenerPeticion(Long idPeticion) {
		if (idPeticion == null) {
			return null;
		}
		return repositorio.findById(idPeticion).get();
	}

	@Override
	public List<PeticionIA> obtenerTodaPeticion() {
		return repositorio.findAll(Sort.by(Sort.Direction.DESC, "estatus"));
	}

	@Override
	@Transactional
	public void actualizarPeticion(Long idPeticion, String estatus) {
		repositorio.actualizarEstatus(idPeticion, estatus);
	}

	@Override
	public PeticionIA generarPeticion(String descripcion) {
		return repositorio.save(new PeticionIA(descripcion));
	}

	@Override
	public void eliminarPeticion(Long idPeticion) {
		if (idPeticion == null) {
			return;
		}
		repositorio.deleteById(idPeticion);
	}

	@Override
	public List<PeticionIA> obtenerPeticionesPorEstatus(String estatus) {
		return repositorio.findByEstatus(estatus);
	}

}
