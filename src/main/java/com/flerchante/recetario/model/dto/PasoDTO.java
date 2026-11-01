package com.flerchante.recetario.model.dto;

import org.springframework.web.multipart.MultipartFile;

import com.flerchante.recetario.model.entity.Paso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class PasoDTO {
	@Getter
	@Setter
	private Paso paso;

	@Getter
	@Setter
	private MultipartFile imagen;
}
