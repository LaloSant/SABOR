package com.flerchante.recetario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACCIONUSUARIO")
@Getter
@Setter
@NoArgsConstructor
public class AccionUsuario {

	public AccionUsuario(Usuario usuario, Receta receta) {
		this.interaccion = Interaccion.FAV;
		this.usuario = usuario;
		this.receta = receta;
	}

	public AccionUsuario(Usuario usuario, Receta receta, Integer detalle) {
		this.interaccion = Interaccion.CAL;
		this.usuario = usuario;
		this.receta = receta;
		this.detalle = detalle;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ACCIONUSUARIO")
	private Long idAccionUsuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RECETA", nullable = false)
	private Receta receta;

	@Enumerated(EnumType.STRING)
	@Column(name = "INTERACCION", nullable = false, length = 3)
	private Interaccion interaccion;

	@Column(name = "DETALLE")
	private Integer detalle; // Nulo o de 0 a 5 en caso de calificación

	public enum Interaccion {
		FAV,
		CAL
	}
}
