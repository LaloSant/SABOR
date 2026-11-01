package com.flerchante.recetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flerchante.recetario.model.entity.PeticionIA;

@Repository
public interface PeticionesRepository extends JpaRepository<PeticionIA, Long> {
	@Modifying
	@Query("UPDATE PeticionIA p SET p.estatus = :estatus WHERE p.idPeticionIA = :idPeticion")
	public void actualizarEstatus(@Param("idPeticion") Long idPeticion, @Param("estatus") String estatus);

	@Modifying
	@Query(value = "INSERT INTO PeticionIA (ESTATUS, DESCRIPCION) VALUES ('PEN', ?1)", nativeQuery = true)
	public void agregarPeticion(String descripcion);

	public List<PeticionIA> findByEstatus(String estatus);
}
