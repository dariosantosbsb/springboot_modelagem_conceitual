package com.dariosantos.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dariosantos.cursomc.security.JWTAuthenticationFilter;
import com.dariosantos.cursomc.security.JWTAuthorizationFilter;
import com.dariosantos.cursomc.security.JWTUtil;
import com.dariosantos.cursomc.services.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JWTUtil jwtUtil;

	// vetorizando uma string para dar permissões de acesso
	private static final String[] PUCLIC_MATCHES = { "/h2-console/**" };

	private static final String[] PUCLIC_MATCHES_GET = { "/categorias/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// liberando o acesso ao banco H2 em ambiente de teste
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		//http.csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUCLIC_MATCHES_GET).permitAll().antMatchers(PUCLIC_MATCHES)
				.permitAll().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsServiceImpl));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCyptPasswordEncoder());
	}

	// permitindo o acesso aos endpoints por multiplas fontes
	// quando esse metodo é instanciado ele sobreescreve o cors
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCyptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
