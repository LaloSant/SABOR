package com.flerchante.recetario.repository;

import com.flerchante.recetario.model.entity.Patrocinador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Long>{
	
}
