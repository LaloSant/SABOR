package com.flerchante.recetario.service.patrocinador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flerchante.recetario.model.entity.Patrocinador;
import com.flerchante.recetario.repository.PatrocinadorRepository;

@Service
public class PatrocinadorServiceImpl implements PatrocinadorService{

	@Autowired
	private PatrocinadorRepository repository;

	@Override
	public List<Patrocinador> listar() {
		return repository.findAll();
	}
	
}
