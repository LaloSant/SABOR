package com.flerchante.recetario.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
// @ToString(exclude = "peticionIA")
@ToString
@Getter
@Setter

@Entity
@Table(name = "PATROCINADOR")
public class Patrocinador {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPatrocinador;

	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;


	@Column(name = "ENLACE", nullable = true, length = 100)
	private String enlace;

	@OneToMany(mappedBy="patrocinador")
	@JsonManagedReference
	private List<Ingrediente> ingredientes;

}
