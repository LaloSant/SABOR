package com.flerchante.recetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flerchante.recetario.model.entity.Rol;

@Repository
//<Entidad, id>
public interface RolRepository extends JpaRepository<Rol, String> {

}
