package com.flerchante.recetario.repository;

import java.sql.Blob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flerchante.recetario.model.entity.Paso;

@Repository
public interface PasoRepository extends JpaRepository<Paso, Long> {

	@Modifying
	@Query("UPDATE Paso p SET p.notas = :notas WHERE p.idPaso = :idPaso")
	public void actualizarPaso(@Param("idPaso") Long idPaso, @Param("notas") String notas);

	@Modifying
	@Query("UPDATE Paso p SET p.notas = :notas, p.imagen = :imagen WHERE p.idPaso = :idPaso")
	public void actualizarPaso(@Param("idPaso") Long idPaso, @Param("notas") String notas, @Param("imagen") Blob imagen);

	@Modifying
	@Query("UPDATE Paso p SET p.peticionIA.idPeticionIA = :idPeticion WHERE p.idPaso = :idPaso")
	public void asignarPeticion(@Param("idPaso") Long idPaso, @Param("idPeticion") Long idPeticion);
}
