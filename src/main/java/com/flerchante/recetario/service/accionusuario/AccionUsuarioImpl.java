package com.flerchante.recetario.service.accionusuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flerchante.recetario.model.entity.AccionUsuario;
import com.flerchante.recetario.model.entity.AccionUsuario.Interaccion;
import com.flerchante.recetario.repository.AccionUsuarioRepository;

@Service
public class AccionUsuarioImpl implements AccionUsuarioService {

	@Autowired
	private AccionUsuarioRepository accionUsuarioRepository;

	@Override
	public AccionUsuario guardar(AccionUsuario accionUsuario) {
		return accionUsuarioRepository.save(accionUsuario);
	}

	@Override
	public void borrar(AccionUsuario accionUsuario) {
		accionUsuarioRepository.delete(accionUsuario);
	}

	@Override
	public Optional<AccionUsuario> obtener(Long idUsuario, Long idReceta, Interaccion interaccion) {
		return accionUsuarioRepository.findByUsuario_IdUsuarioAndReceta_IdRecetaAndInteraccion(idUsuario, idReceta,
				interaccion);
	}

	@Override
	public List<AccionUsuario> obtenerAcciones(Long idUsuario) {
		return accionUsuarioRepository.findByUsuario_IdUsuario(idUsuario);
	}

	@Override
	public List<AccionUsuario> obtenerAccionesReceta(Long idReceta) {
		return accionUsuarioRepository.findByReceta_IdReceta(idReceta);
	}

	@Override
	public List<AccionUsuario> obtenerAcciones(Long idUsuario, Interaccion interaccion) {
		return accionUsuarioRepository.findByUsuario_IdUsuarioAndInteraccion(idUsuario, interaccion);
	}

}
