package com.flerchante.recetario.service.rol;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flerchante.recetario.model.entity.Rol;
import com.flerchante.recetario.repository.RolRepository;

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
