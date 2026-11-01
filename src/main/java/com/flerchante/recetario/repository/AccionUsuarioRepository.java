package com.flerchante.recetario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flerchante.recetario.model.entity.AccionUsuario;
import com.flerchante.recetario.model.entity.AccionUsuario.Interaccion;

@Repository
public interface AccionUsuarioRepository extends JpaRepository<AccionUsuario, Long> {
	Optional<AccionUsuario> findByUsuario_IdUsuarioAndReceta_IdRecetaAndInteraccion(Long idUsuario, Long idReceta, Interaccion interaccion);

	// Buscar todas las acciones de un usuario
	List<AccionUsuario> findByUsuario_IdUsuario(Long idUsuario);

	// Buscar todas las acciones sobre una receta
	List<AccionUsuario> findByReceta_IdReceta(Long idReceta);

	// Buscar todas las acciones de un usuario filtradas por tipo de interacción (FAV o CAL)
	List<AccionUsuario> findByUsuario_IdUsuarioAndInteraccion(Long idUsuario, Interaccion interaccion);
}
