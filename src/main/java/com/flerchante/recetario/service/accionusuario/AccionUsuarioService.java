package com.flerchante.recetario.service.accionusuario;

import java.util.List;
import java.util.Optional;

import com.flerchante.recetario.model.entity.AccionUsuario;
import com.flerchante.recetario.model.entity.AccionUsuario.Interaccion;

public interface AccionUsuarioService {

	public AccionUsuario guardar(AccionUsuario accionUsuario);

	public void borrar(AccionUsuario accionUsuario);

	public Optional<AccionUsuario> obtener(Long idUsuario, Long idReceta, Interaccion interaccion);

	public List<AccionUsuario> obtenerAcciones(Long idUsuario);

	public List<AccionUsuario> obtenerAccionesReceta(Long idReceta);

	public List<AccionUsuario> obtenerAcciones(Long idUsuario, Interaccion interaccion);
}
