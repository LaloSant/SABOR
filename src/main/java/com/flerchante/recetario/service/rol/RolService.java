package com.flerchante.recetario.service.rol;

import java.util.Optional;

import com.flerchante.recetario.model.entity.Rol;

public interface  RolService {
    public Optional<Rol> obtenerRolPorID(String idRol);
}
