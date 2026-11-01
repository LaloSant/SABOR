package com.flerchante.recetario.service.paso;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flerchante.recetario.model.entity.Paso;
import com.flerchante.recetario.model.entity.Receta;
import com.flerchante.recetario.repository.PasoRepository;

import jakarta.transaction.Transactional;

@Service
public class PasoServiceImpl implements PasoService {

	@Autowired
	PasoRepository repositorio;

	@Override
	@Transactional
	public void actualizarPaso(Long idPaso, String notas) {
		repositorio.actualizarPaso(idPaso, notas);
	}

	@Override
	@Transactional
	public void actualizarPaso(Long idPaso, String notas, Blob imagen) {
		repositorio.actualizarPaso(idPaso, notas, imagen);
	}

	@Override
	public Paso obtenerPaso(Long idPaso) {
		if (idPaso == null) {
			return null;
		}
		return repositorio.findById(idPaso).get();
	}

	@Override
	public void guardarPasos(List<Paso> pasos, Receta receta) {
		if (pasos != null) {
			for (int i = 0; i < pasos.size(); i++) {
				Paso paso = pasos.get(i);
				paso.setReceta(receta);
				paso.setIndicePaso(i + 1l);
				repositorio.save(paso);
			}
		}
	}

	@Override
	public void guardarPasos(List<Paso> pasos, Receta receta, Long indicePaso) {
		if (pasos != null) {
			for (int i = 0; i < pasos.size(); i++) {
				Paso paso = pasos.get(i);
				paso.setReceta(receta);
				paso.setIndicePaso(indicePaso);
				repositorio.save(paso);
			}
		}
	}

	@Override
	public void eliminarPaso(Paso paso) {
		if (paso == null) {
			return;
		}
		repositorio.delete(paso);
	}

	@Override
	public void guardarPaso(Paso paso) {
		if (paso == null) {
			return;
		}
		repositorio.save(paso);
	}

	@Override
	public List<Paso> obtenerTodoPaso() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public void asignarPeticionIA(Long idPaso, Long idPeticionIA) {
		repositorio.asignarPeticion(idPaso, idPeticionIA);
	}

}
