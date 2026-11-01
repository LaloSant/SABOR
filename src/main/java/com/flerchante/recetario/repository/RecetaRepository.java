package com.flerchante.recetario.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flerchante.recetario.model.entity.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

	public List<Receta> findAllByOrderByVisitasSemanalesDesc();

	public List<Receta> findAllByOrderByVisitasTotalesDesc();

	public List<Receta> findBynombreIgnoreCaseContaining(String nombre);

	@Modifying
	@Query("UPDATE Receta r SET r.visitasSemanales = r.visitasSemanales + 1, r.visitasTotales = r.visitasTotales + 1 WHERE r.idReceta = :idReceta")
	public void aumentarVisita(@Param("idReceta") Long idReceta);

	@Modifying
	@Query("UPDATE Receta r SET r.nombre = :nombre WHERE r.idReceta = :idReceta")
	public void actualizarNombre(@Param("idReceta") Long idReceta, @Param("nombre") String nombre);

	@Modifying
	@Query("UPDATE Receta r SET r.calificacion = :calificacion, r.calificacionesTotales = :calificacionesTotales WHERE r.idReceta = :idReceta")
	public void actualizarCalificacion(@Param("idReceta") Long idReceta, @Param("calificacion") Double calificacion,
			@Param("calificacionesTotales") Long calificacionesTotales);

}
