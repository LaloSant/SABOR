package com.flerchante.recetario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

@Entity
@Table(name = "DETALLES")
public class Detalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalle;

	@ManyToOne
	@JoinColumn(name = "ID_RECETA")
	private Receta receta;

	@ManyToOne
	@JoinColumn(name = "ID_INGREDIENTE")
	private Ingrediente ingrediente;

	@Column(name = "CANTIDAD", nullable = false)
	private Double cantidad;

	@Column(name = "COSTO", nullable = false)
	private Double costo;

	public Double calcularCosto() {
		return ingrediente.getCostoUnitario() * cantidad;
	}

}
