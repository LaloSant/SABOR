package com.flerchante.recetario.model.entity;

import java.util.ArrayList;
import java.util.List;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "USUARIOS")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "CONTRASENIA", nullable = false)
	private String contrasenia;

	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	private Rol rol;

	@OneToMany(mappedBy = "usuario")
	private List<Receta> recetas;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AccionUsuario> acciones = new ArrayList<>();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usuario{");
		sb.append("idUsuario=").append(idUsuario);
		sb.append(", nombre=").append(nombre);
		sb.append(", contrasenia=").append("[PROTEGIDO]");
		sb.append(", email=").append(email);
		sb.append(", rol=").append(rol.getIdRol());
		sb.append('}');
		return sb.toString();
	}

}
