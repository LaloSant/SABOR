package com.flerchante.sabor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
class RecetarioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void testIndexPage() throws Exception {
		mockMvc.perform(get("/index"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	void testLoginPage() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"))
				.andExpect(model().attributeExists("usuario"));
	}

	@Test
	void testRegistroPage() throws Exception {
		mockMvc.perform(get("/registro"))
				.andExpect(status().isOk())
				.andExpect(view().name("registro"))
				.andExpect(model().attributeExists("usuario"));
	}

	@Test
	void testListarRecetas() throws Exception {
		mockMvc.perform(get("/recetas"))
				.andExpect(status().isOk())
				.andExpect(view().name("vistas/recetas/descubrir"))
				.andExpect(model().attributeExists("recetas"));
	}

	@Test
	void testagregarPage() throws Exception {
		mockMvc.perform(get("/receta/agregar"))
				.andExpect(status().isOk())
				.andExpect(view().name("vistas/recetas/agregar"));
	}

}
