package com.dariosantos.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dariosantos.cursomc.domain.Categoria;
import com.dariosantos.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService; 

	//<?> pode ser qualquer tipo
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
		
	}
}
