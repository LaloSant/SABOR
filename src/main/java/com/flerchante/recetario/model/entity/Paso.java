package com.flerchante.recetario.model.entity;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
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
@Table(name = "PASOS")
public class Paso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaso;

	@ManyToOne
	@JoinColumn(name = "ID_RECETA")
	private Receta receta;

	@Column(name = "INDICE_PASO", nullable = false)
	private Long indicePaso;

	@Column(name = "NOTAS", nullable = false, length = 300)
	private String notas;

	@Column(name = "IMAGEN", nullable = true)
	private Blob imagen;

	@OneToOne
	@JoinColumn(name = "ID_PET_IA", referencedColumnName = "ID_PETICION_IA")
	private PeticionIA peticionIA;

	@Transient
	private boolean generarPeticion;
}
