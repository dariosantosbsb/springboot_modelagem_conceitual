package com.dariosantos.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dariosantos.cursomc.domain.Cliente;
import com.dariosantos.cursomc.dtos.ClienteDTO;
//import com.dariosantos.cursomc.dtos.ClienteDTO;
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
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> cliente = clienteService.findAll();
		List<ClienteDTO> clienteDto = cliente.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList()); // converte uma lista na outra
		return ResponseEntity.ok().body(clienteDto);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, // campo nome
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> cliente = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> clienteDto = cliente.map(obj -> new ClienteDTO(obj)); // converte uma lista na outra
		return ResponseEntity.ok().body(clienteDto);
	}

	/*@PostMapping()
	public ResponseEntity<Void> create(@Valid @RequestBody ClienteDTO clienteDTO) {// @RequestBody converte json em obj java.
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build(); // created retorna o cod 201
	}*/

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDTO(clienteDto);
		cliente.setId(id);
		clienteService.update(cliente);
		return ResponseEntity.noContent().build(); // noContent retorna 204
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build(); // noContent retorna 204
	}

}
