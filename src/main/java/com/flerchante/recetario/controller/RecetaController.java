package com.flerchante.recetario.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flerchante.recetario.model.dto.PasoDTO;
import com.flerchante.recetario.model.dto.RecetaCompDTO;
import com.flerchante.recetario.model.entity.AccionUsuario;
import com.flerchante.recetario.model.entity.Detalle;
import com.flerchante.recetario.model.entity.Paso;
import com.flerchante.recetario.model.entity.PeticionIA;
import com.flerchante.recetario.model.entity.Receta;
import com.flerchante.recetario.model.entity.Usuario;
import com.flerchante.recetario.model.entity.AccionUsuario.Interaccion;
import com.flerchante.recetario.service.accionusuario.AccionUsuarioImpl;
import com.flerchante.recetario.service.detalle.DetalleServiceImpl;
import com.flerchante.recetario.service.ingrediente.IngredienteServiceImpl;
import com.flerchante.recetario.service.paso.PasoServiceImpl;
import com.flerchante.recetario.service.peticiones.PeticionesServiceImpl;
import com.flerchante.recetario.service.receta.RecetaServiceImpl;
import com.flerchante.recetario.service.usuario.UserUtils;
import com.flerchante.recetario.service.usuario.UsuarioServiceImpl;

@Controller
public class RecetaController {

	private static final String RUTA_VISTA = "vistas/recetas/";

	@Autowired
	private RecetaServiceImpl recetaRepositorio;

	@Autowired
	private UsuarioServiceImpl usuarioRepositorio;

	@Autowired
	private UserUtils userUtils;

	@Autowired
	private IngredienteServiceImpl ingredienteRepositorio;

	@Autowired
	private PasoServiceImpl pasoRepositorio;

	@Autowired
	private DetalleServiceImpl detalleRepositorio;

	@Autowired
	private PeticionesServiceImpl peticionRepositorio;

	@Autowired
	private AccionUsuarioImpl accionUsuarioRepositorio;

	@GetMapping("/recetas/misRecetas")
	public String getMisRecetas(Model modelo,
			@RequestParam(required = false) List<Long> ingredientes,
			@RequestParam(required = false) Integer exclusivo) {

		Usuario user = usuarioRepositorio.getUsuarioSesion();
		userUtils.setUserSession(modelo);

		modelo.addAttribute("ingredientes", ingredienteRepositorio.listarTodo());
		modelo.addAttribute("ingredientesSel", ingredientes);
		modelo.addAttribute("tipoVista", "misRecetas");
		List<Receta> recetas = recetaRepositorio.listarTodaReceta().stream()
				.filter(r -> r.getUsuario().equals(user)).toList();
		if (ingredientes == null) {
			modelo.addAttribute("recetas", recetas);
			return RUTA_VISTA + "descubrir";
		}
		filtroIngrediente(modelo, ingredientes, exclusivo == null ? 0 : exclusivo, recetas);
		return RUTA_VISTA + "descubrir";
	}

	@GetMapping("/recetas/misFavoritas")
	public String getMisRecetasFavoritas(Model modelo,
			@RequestParam(required = false) List<Long> ingredientes,
			@RequestParam(required = false, defaultValue = "0") Integer exclusivo) {

		Usuario user = usuarioRepositorio.getUsuarioSesion();
		userUtils.setUserSession(modelo);
		modelo.addAttribute("ingredientes", ingredienteRepositorio.listarTodo());
		modelo.addAttribute("ingredientesSel", ingredientes);
		modelo.addAttribute("tipoVista", "misFavoritas");

		List<AccionUsuario> acciones = accionUsuarioRepositorio.obtenerAcciones(user.getIdUsuario(), Interaccion.FAV);
		List<Receta> recetas = new ArrayList<>(acciones.stream().map(AccionUsuario::getReceta).toList());
		if (ingredientes == null) {
			modelo.addAttribute("recetas", recetas);
			return RUTA_VISTA + "descubrir";
		}
		filtroIngrediente(modelo, ingredientes, exclusivo == null ? 0 : exclusivo, recetas);
		return RUTA_VISTA + "descubrir";
	}

	private void filtroIngrediente(Model modelo, List<Long> ingredientes, int exclusivo, List<Receta> recetas) {
		List<Receta> listaExclusivo = new ArrayList<>();
		List<Receta> listaNoExclusivo = new ArrayList<>();
		for (Receta receta : recetas) {
			boolean hitNoExclusivo = false;
			int hitExclusivo = 0;
			List<Detalle> detalles = receta.getDetalles();
			for (Detalle detalle : detalles) {
				if (ingredientes.contains(detalle.getIngrediente().getIdIngrediente())) {
					hitNoExclusivo = true;
					hitExclusivo++;
				}
			}
			if (hitNoExclusivo) {
				listaNoExclusivo.add(receta);
			}
			if (hitExclusivo >= ingredientes.size()) {
				listaExclusivo.add(receta);
			}
		}
		modelo.addAttribute("recetas", exclusivo == 1 ? listaExclusivo : listaNoExclusivo);
		modelo.addAttribute("exclusivo", exclusivo);
	}

	@GetMapping({ "/recetas", "/" })
	public String listarRecetas(Model modelo,
			@RequestParam(required = false, defaultValue = "calificacion") String ordenarPor,
			@RequestParam(required = false) String nombreReceta,
			@RequestParam(required = false) List<Long> ingredientes,
			@RequestParam(required = false) Integer exclusivo) {

		userUtils.setUserSession(modelo);
		modelo.addAttribute("ingredientes", ingredienteRepositorio.listarTodo());
		modelo.addAttribute("ingredientesSel", ingredientes);
		List<Receta> recetas;
		if (nombreReceta != null && !nombreReceta.isBlank()) {
			recetas = recetaRepositorio.obtenerTodoPor(nombreReceta);
			modelo.addAttribute("recetas", recetas);
			return RUTA_VISTA + "descubrir";
		}

		//if (ingredientes != null) {
		// if (exclusivo != null && exclusivo == 1) {
		// 	modelo.addAttribute("exclusivo", 1);
		// 	List<Long> recetasIds = detalleRepositorio.buscarPorIngredientes(
		// 			ingredientes, (long) ingredientes.size());
		// 	recetas = recetaRepositorio.obtenerTodoPor(recetasIds);
		// 	modelo.addAttribute("recetas", recetas);
		// 	modelo.addAttribute("ingredientesSel", ingredientes);
		// 	return RUTA_VISTA + "descubrir";
		// }
		// List<Detalle> detalles = detalleRepositorio.buscarPorIngredientes(ingredientes);
		// Set<Receta> set = new HashSet<>();
		// for (Detalle detalle : detalles) {
		// 	set.add(detalle.getReceta());
		// }
		// recetas = new ArrayList<>(set);
		// modelo.addAttribute("exclusivo", 0);
		// modelo.addAttribute("recetas", recetas);
		// modelo.addAttribute("ingredientesSel", ingredientes);
		// return RUTA_VISTA + "descubrir";
		//}
		if ("semana".equals(ordenarPor)) {
			recetas = recetaRepositorio.obtenerTopSemana();
			modelo.addAttribute("textoOrden", "Visitas semanales");
		} else if ("total".equals(ordenarPor)) {
			recetas = recetaRepositorio.obtenerTopTotal();
			modelo.addAttribute("textoOrden", "Visitas totales");
		} else {
			recetas = recetaRepositorio.listarTodaReceta().stream()
					.sorted(Comparator.comparing(Receta::getCalificacion).reversed()).limit(10).toList();
			modelo.addAttribute("textoOrden", "Calificacion");
		}
		if (ingredientes != null) {
			filtroIngrediente(modelo, ingredientes, exclusivo == null ? 0 : exclusivo, recetas);
			return RUTA_VISTA + "descubrir";
		}

		// recetas = recetas.stream().limit(10).toList();
		modelo.addAttribute("recetas", recetas);

		return RUTA_VISTA + "descubrir";
	}

	@PostMapping("/recetas/calificar")
	public String calificarReceta(@RequestParam Long idReceta,
			@RequestParam("calificacion") int calificacionNueva) {
		Optional<Receta> recetaOpt = recetaRepositorio.obtenerRecetaPorID(idReceta);
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (recetaOpt.isPresent()) {
			Receta receta = recetaOpt.get();
			Optional<AccionUsuario> auCalifOpt = accionUsuarioRepositorio
					.obtener(usuarioRepositorio.getUsuarioSesion().getIdUsuario(), idReceta, Interaccion.CAL);
			AccionUsuario au = null;
			if (!auCalifOpt.isPresent()) {
				au = new AccionUsuario(user, receta, calificacionNueva);
				receta.actualizarCalificacion(calificacionNueva);
			} else {
				au = auCalifOpt.get();
				receta.actualizarCalificacion(calificacionNueva, au.getDetalle());
				au.setDetalle(calificacionNueva);
			}
			accionUsuarioRepositorio.guardar(au);
			recetaRepositorio.actualizarCalificacion(idReceta, receta.getCalificacion(),
					receta.getCalificacionesTotales());
		}
		return String.format("redirect:/recetas/ver?idReceta=%d", idReceta);
	}

	@PostMapping("/recetas/favorita")
	public String favoritaReceta(@RequestParam Long idReceta) {
		Optional<Receta> recetaOpt = recetaRepositorio.obtenerRecetaPorID(idReceta);
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (recetaOpt.isPresent() && user != null) {
			Optional<AccionUsuario> auFavOpt = accionUsuarioRepositorio
					.obtener(usuarioRepositorio.getUsuarioSesion().getIdUsuario(), idReceta, Interaccion.FAV);
			if (!auFavOpt.isPresent()) {
				AccionUsuario au = new AccionUsuario(user, recetaOpt.get());
				accionUsuarioRepositorio.guardar(au);
			} else {
				accionUsuarioRepositorio.borrar(auFavOpt.get());
			}
		}
		return String.format("redirect:/recetas/ver?idReceta=%d", idReceta);
	}

	@GetMapping("/recetas/ver")
	public String ver(Model modelo, @RequestParam(required = false, defaultValue = "1") Long idReceta) {
		userUtils.setUserSession(modelo);
		Optional<Receta> recetaOpt = recetaRepositorio.obtenerRecetaPorID(idReceta);
		if (!recetaOpt.isPresent()) {
			return "redirect:/recetas";
		}
		Receta receta = recetaOpt.get();
		recetaRepositorio.aumentarVisita(idReceta);
		if (usuarioRepositorio.getUsuarioSesion() != null) {
			Optional<AccionUsuario> auCalifOpt = accionUsuarioRepositorio
					.obtener(usuarioRepositorio.getUsuarioSesion().getIdUsuario(), idReceta, Interaccion.CAL);
			Optional<AccionUsuario> auFavOpt = accionUsuarioRepositorio
					.obtener(usuarioRepositorio.getUsuarioSesion().getIdUsuario(), idReceta, Interaccion.FAV);
			modelo.addAttribute("auCalifOpt", auCalifOpt.isPresent() ? auCalifOpt.get() : null);
			modelo.addAttribute("auFavOpt", auFavOpt.isPresent() ? auFavOpt.get() : null);
		}
		modelo.addAttribute("receta", receta);
		modelo.addAttribute("costo", receta.calcularCosto());
		return RUTA_VISTA + "ver";
	}

	@GetMapping("/recetas/editar")
	public String editar(Model modelo, @RequestParam(required = false) Long idReceta) {
		userUtils.setUserSession(modelo);
		Optional<Receta> recetaOpt = recetaRepositorio.obtenerRecetaPorID(idReceta);
		if (!recetaOpt.isPresent()) {
			return "redirect:/recetas";
		}
		Receta rec = recetaOpt.get();
		List<PasoDTO> pasoImagenes = new ArrayList<>();
		for (int i = 0; i < rec.getPasos().size(); i++) {
			pasoImagenes.add(new PasoDTO(rec.getPasos().get(i), null));
		}
		RecetaCompDTO dto = new RecetaCompDTO(rec, rec.getPasos(), rec.getDetalles(), null, pasoImagenes);
		modelo.addAttribute("ingredientes", ingredienteRepositorio.listarTodo());
		modelo.addAttribute("dto", dto);
		return RUTA_VISTA + "editar";
	}

	@PostMapping("/recetas/editar")
	public String actualizarReceta(@ModelAttribute RecetaCompDTO dto) throws IOException {
		if (dto == null || dto.getReceta() == null || dto.getReceta().getIdReceta() == null) {
			return "redirect:/recetas";
		}
		Optional<Receta> recetaOpt = recetaRepositorio.obtenerRecetaPorID(dto.getReceta().getIdReceta());
		if (!recetaOpt.isPresent()) {
			return "redirect:/recetas";
		}
		Receta recetaExistente = recetaOpt.get();
		List<Paso> pasos = dto.getPasos();
		List<PasoDTO> pasoImagenes = dto.getPasoDTOs();

		if (pasos != null) {
			for (int i = 0; i < pasos.size(); i++) {
				Paso paso = pasos.get(i);
				if (paso.getIdPaso() != null && paso.getNotas() != null) {
					Paso pasoExistente = pasoRepositorio.obtenerPaso(paso.getIdPaso());
					pasoExistente.setNotas(paso.getNotas());
					if (pasoImagenes != null && i < pasoImagenes.size() && pasoImagenes.get(i).getImagen() != null
							&& !pasoImagenes.get(i).getImagen().isEmpty()) {
						pasoExistente.setImagen(ImagenController.mpfTBlob(pasoImagenes.get(i).getImagen()));
					}
					pasoRepositorio.actualizarPaso(pasoExistente.getIdPaso(), pasoExistente.getNotas(),
							pasoExistente.getImagen());
				} else {
					pasoRepositorio.guardarPasos(Arrays.asList(paso), recetaExistente, i + 1l); // nuevo
				}
			}
		}
		List<Detalle> detalles = dto.getDetalles();
		if (detalles != null) {
			for (Detalle detalle : detalles) {
				if (detalle.getIdDetalle() != null && detalle.getCantidad() != null
						&& detalle.getIngrediente() != null && detalle.getIngrediente().getIdIngrediente() != null) {
					detalleRepositorio.actualizarDetalle(detalle.getIdDetalle(), detalle.getCantidad(),
							detalle.getIngrediente().getIdIngrediente());
				} else if (detalle.getIdDetalle() == null) {
					detalleRepositorio.guardarDetalles(Arrays.asList(detalle), recetaExistente);
				}
			}
		}
		if (dto.getImagen() != null && !dto.getImagen().isEmpty()) {
			recetaExistente.setImagen(ImagenController.mpfTBlob(dto.getImagen()));
			recetaRepositorio.guardarReceta(recetaExistente);
		}

		if (dto.getReceta().getNombre() != null) {
			recetaRepositorio.actualizarNombre(recetaExistente.getIdReceta(), dto.getReceta().getNombre());
		}

		return "redirect:/recetas";
	}

	@GetMapping("/receta/agregar")
	public String agregarVista(Model modelo) {
		RecetaCompDTO dto = new RecetaCompDTO();
		PasoDTO pdto = new PasoDTO();
		userUtils.setUserSession(modelo);
		modelo.addAttribute("dto", dto);
		modelo.addAttribute("pdto", pdto);
		modelo.addAttribute("ingredientes", ingredienteRepositorio.listarTodo());
		return RUTA_VISTA + "agregar";
	}

	@PostMapping("/receta/agregar")
	public String agregar(@ModelAttribute("dto") RecetaCompDTO dto) {
		Usuario user = usuarioRepositorio.getUsuarioSesion();
		if (user == null) {
			return "redirect:/login";
		}
		Receta nuevaReceta = dto.getReceta();
		nuevaReceta.setUsuario(user);
		nuevaReceta.setImagen(ImagenController.mpfTBlob(dto.getImagen()));

		Receta recetaGuardada = recetaRepositorio.guardarReceta(nuevaReceta);
		List<Paso> pasos = dto.getPasos();
		if (pasos != null) {
			for (Paso paso : pasos) {
				pasoRepositorio.guardarPasos(pasos, recetaGuardada);
				if (paso.isGenerarPeticion()) {
					PeticionIA peticionGenerada = peticionRepositorio.generarPeticion(paso.getNotas());
					paso.setPeticionIA(peticionGenerada);
					peticionGenerada.setPaso(paso);
					pasoRepositorio.asignarPeticionIA(paso.getIdPaso(), paso.getPeticionIA().getIdPeticionIA());
				}
			}
		}
		List<Detalle> detalles = dto.getDetalles();
		detalleRepositorio.guardarDetalles(detalles, recetaGuardada);
		return "redirect:/recetas";
	}

	@GetMapping("/recetas/eliminar")
	public String eliminarReceta(Model modelo, @RequestParam(required = true) Long idReceta,
			@RequestParam(required = true) String nom) {
		Optional<Receta> recetaOpt = recetaRepositorio.obtenerRecetaPorID(idReceta);
		if (!recetaOpt.isPresent() || !recetaOpt.get().getUsuario().getNombre().equals(nom)) {
			return "redirect:/recetas";
		}
		for (Detalle detalle : recetaOpt.get().getDetalles()) {
			detalleRepositorio.eliminarDetalle(detalle);
		}
		for (Paso paso : recetaOpt.get().getPasos()) {
			PeticionIA peticion = paso.getPeticionIA();
			if (peticion != null) {
				peticion.setPaso(null);
				paso.setPeticionIA(null);
				peticionRepositorio.eliminarPeticion(peticion.getIdPeticionIA());
			}
			pasoRepositorio.eliminarPaso(paso);
		}
		recetaRepositorio.eliminarReceta(idReceta);
		return "redirect:/recetas";
	}

}
