package com.dariosantos.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dariosantos.cursomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping(value = "/listar")
	public List<Categoria> listar() {

		Categoria cat1 = new Categoria(1, "novo");
		Categoria cat2 = new Categoria(2, "velho");

		List<Categoria> lista = new ArrayList<Categoria>();

		lista.add(cat1);
		lista.add(cat2);

		return lista;

	}
}
