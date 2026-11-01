package com.flerchante.recetario.security;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.flerchante.recetario.model.entity.Usuario;
import com.flerchante.recetario.service.usuario.UsuarioService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private final UsuarioService usuarioService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuario(username);
			if (usuarioOpt.isEmpty()) {
				throw new UsernameNotFoundException(username);
			}
			Usuario u = usuarioOpt.get();
			return User.builder()
					.username(u.getEmail())
					.password(u.getContrasenia())
					.roles(u.getRol() != null && u.getRol().getNombre() != null ? u.getRol().getNombre() : "USER")
					.build();
		};
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.disable())
				.formLogin(form -> form
						.loginPage("/login").permitAll()
						.usernameParameter("email")
						.passwordParameter("contrasenia")
						.defaultSuccessUrl("/recetas", true)
						.failureUrl("/login?error"))
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout")
						.permitAll())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/index", "/home", "/login", "/registro", "/css/**", "/img/**", "/js/**",
								"/static/**", "/recetas", "/recetas/ver", "/recetas/imagen/**", "/pasos/imagen/**", "/ingredientes/**", "/patrocinadores")
						.permitAll()
						.anyRequest().authenticated());
		return http.build();
	}
}
