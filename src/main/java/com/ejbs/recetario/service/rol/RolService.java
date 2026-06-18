package com.ejbs.recetario.service.rol;

import java.util.Optional;

import com.ejbs.recetario.model.entity.Rol;

public interface  RolService {
    public Optional<Rol> obtenerRolPorID(String idRol);
}
