package com.ejbs.recetario.service.rol;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejbs.recetario.model.entity.Rol;
import com.ejbs.recetario.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	RolRepository rolRepository;

	@Override
	public Optional<Rol> obtenerRolPorID(String idRol) {
		if (idRol == null) {
			return null;
		}
		return rolRepository.findById(idRol);
	}

}
