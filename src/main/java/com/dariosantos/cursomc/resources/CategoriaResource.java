package com.dariosantos.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dariosantos.cursomc.domain.Categoria;
import com.dariosantos.cursomc.dtos.CategoriaDTO;
import com.dariosantos.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	// <?> pode ser qualquer tipo
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}

	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categoria = categoriaService.findAll();
		List<CategoriaDTO> categoriaDto = categoria.stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList()); // converte uma lista na outra
		return ResponseEntity.ok().body(categoriaDto);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, // campo nome
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> categoria = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> categoriaDto = categoria.map(obj -> new CategoriaDTO(obj)); // converte uma lista na outra
		return ResponseEntity.ok().body(categoriaDto);
	}

	@PostMapping()
	public ResponseEntity<Void> create(@Valid @RequestBody CategoriaDTO categoriaDTO) {// @RequestBody converte json em obj java.
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build(); // created retorna o cod 201
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
		categoria.setId(id);
		categoriaService.update(categoria);
		return ResponseEntity.noContent().build(); // noContent retorna 204
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build(); // noContent retorna 204
	}

}
