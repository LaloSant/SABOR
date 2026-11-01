package com.flerchante.recetario.model.dto;

import org.springframework.web.multipart.MultipartFile;

import com.flerchante.recetario.model.entity.PeticionIA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PeticionDTO {
	@Getter
	@Setter
	private PeticionIA peticion;

	@Getter
	@Setter
	private MultipartFile mpf;
}
