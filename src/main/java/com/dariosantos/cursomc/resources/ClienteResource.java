package com.dariosantos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dariosantos.cursomc.domain.Cliente;
import com.dariosantos.cursomc.repositories.ClienteRepository;
import com.dariosantos.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteRepository clienteRepository;

	// <?> pode ser qualquer tipo
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente categoria = clienteService.find(id);
		return ResponseEntity.ok().body(categoria);
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Cliente categoria) {
		clienteRepository.save(categoria);
		return ResponseEntity.ok().body(categoria);
	}

}
