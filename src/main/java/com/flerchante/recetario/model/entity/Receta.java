package com.flerchante.recetario.model.entity;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RECETAS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReceta;

	@Column(name = "NOMBRE", length = 50, nullable = false)
	private String nombre;

	@Column(name = "IMAGEN")
	@JsonIgnore
	private Blob imagen;

	@Column(name = "VISITAS_TOTALES")
	private Long visitasTotales;

	@Column(name = "VISITAS_SEMANALES")
	private Long visitasSemanales;

	@Column(name = "CALIFICACIONES_TOTALES")
	private Long calificacionesTotales;

	@DecimalMin("0.0")
	@DecimalMax("5.0")
	// @Column(name = "CALIFICACION", columnDefinition = "NUMBER CHECK (CALIFICACION >= 0 AND CALIFICACION <= 5)")
	@Column(name = "CALIFICACION")
	private Double calificacion;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@OneToMany(mappedBy = "receta")
	private List<Detalle> detalles;

	@OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AccionUsuario> acciones = new ArrayList<>();

	@Setter
	@OneToMany(mappedBy = "receta")
	private List<Paso> pasos;

	public List<Paso> getPasos() {
		return pasos.stream().sorted(Comparator.comparing(Paso::getIdPaso)).collect(Collectors.toList());
	}

	public String calcularCosto() {
		double costo = 0d;
		String formateado = "0";
		for (Detalle detalle : detalles) {
			costo += detalle.calcularCosto();
			formateado = String.format("%.2f", costo);
		}
		return formateado;
	}

	public Double actualizarCalificacion(int calificacionNueva) {
		double mult = calificacion * calificacionesTotales;
		mult += calificacionNueva;
		calificacionesTotales++;
		calificacion = roundAvoid(mult / calificacionesTotales, 2);
		return calificacion;
	}

	public Double actualizarCalificacion(int calificacionNueva, int calificacionVieja) {
		double mult = calificacion * calificacionesTotales;
		mult += (calificacionNueva - calificacionVieja);
		calificacion = roundAvoid(mult / calificacionesTotales, 2);
		return calificacion;
	}

	private static double roundAvoid(double value, int places) {
		double scale = Math.pow(10, places);
		return Math.round(value * scale) / scale;
	}
}
