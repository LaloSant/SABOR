package com.flerchante.recetario.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flerchante.recetario.model.entity.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {
	public List<Detalle> findByReceta_IdReceta(Long idReceta);

	@Modifying
	@Query(value = "UPDATE DETALLES SET CANTIDAD = :cantidad, ID_INGREDIENTE = :idIngrediente WHERE ID_DETALLE = :idDetalle", nativeQuery = true)
	public void actualizarDetalle(@Param("idDetalle") Long idDetalle, @Param("cantidad") Double cantidad,
			@Param("idIngrediente") Long idIngrediente);

	@Query("SELECT d FROM Detalle d WHERE d.ingrediente.idIngrediente IN :ids")
	public List<Detalle> findByIngredientes(@Param("ids") List<Long> ids);

	@Query("SELECT d.receta.idReceta " +
			"FROM Detalle d " +
			"WHERE d.ingrediente.idIngrediente IN :ids " +
			"GROUP BY d.receta.idReceta " +
			"HAVING COUNT(DISTINCT d.ingrediente) = :size")
	public List<Long> recetasPorIngredientes(@Param("ids") List<Long> ids,
			@Param("size") long size);

}
