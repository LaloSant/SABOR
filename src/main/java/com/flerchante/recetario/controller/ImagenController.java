package com.flerchante.recetario.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.flerchante.recetario.service.ingrediente.IngredienteServiceImpl;
import com.flerchante.recetario.service.paso.PasoServiceImpl;
import com.flerchante.recetario.service.receta.RecetaServiceImpl;

@Controller
public class ImagenController {

	@Autowired
	private RecetaServiceImpl recetaRepository;

	@Autowired
	private PasoServiceImpl pasoRepository;

	@Autowired
	private IngredienteServiceImpl ingredienteRepository;

	@GetMapping("/recetas/imagen/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> obtenerImagen(@PathVariable("id") Long recetaId) {
		Blob img = recetaRepository.obtenerRecetaPorID(recetaId).get().getImagen();
		if (img == null) {
			return ResponseEntity.status(404).build();
		}
		byte[] bytes = null;
		try {
			bytes = img.getBytes(1, (int) img.length());
		} catch (SQLException ex) {
		}
		String mimeType = "image/jpeg";
		if (bytes == null || bytes.length == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(mimeType))
				.body(bytes);
	}

	@GetMapping("/pasos/imagen/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> obtenerImagenPaso(@PathVariable("id") Long pasoId) {
		Blob img = pasoRepository.obtenerPaso(pasoId).getImagen();
		if (img == null) {
			return ResponseEntity.status(404).build();
		}
		byte[] bytes = null;
		try {
			bytes = img.getBytes(1, (int) img.length());
		} catch (SQLException ex) {
		}
		String mimeType = "image/jpeg";
		if (bytes == null || bytes.length == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(mimeType))
				.body(bytes);
	}

	@GetMapping("/ingredientes/imagen/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> obtenerImagenIngrediente(@PathVariable("id") Long idIngrediente) {
		Blob img = ingredienteRepository.obtener(idIngrediente).getImagen();
		if (img == null) {
			return ResponseEntity.status(404).build();
		}
		byte[] bytes = null;
		try {
			bytes = img.getBytes(1, (int) img.length());
		} catch (SQLException ex) {
		}
		String mimeType = "image/jpeg";
		if (bytes == null || bytes.length == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(mimeType))
				.body(bytes);
	}

	public static Blob mpfTBlob(MultipartFile mpf) {
		if (mpf != null && !mpf.isEmpty()) {
			try {
				byte[] bytes = mpf.getBytes();
				Blob blob = new SerialBlob(bytes);
				return blob;
			} catch (IOException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

}
