package com.dariosantos.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dariosantos.cursomc.domain.Pedido;
import com.dariosantos.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	// <?> pode ser qualquer tipo
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}

	@PostMapping()
	public ResponseEntity<Void> create(@Valid @RequestBody Pedido pedido) {// @RequestBody converte json em obj java.
		pedidoService.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();
		return ResponseEntity.created(uri).build(); // created retorna o cod 201
	}
	
	/*@PostMapping()
	public ResponseEntity<?> create(@RequestBody Pedido pedido) {
		pedidoRepository.save(pedido);
		return ResponseEntity.ok().body(pedido);
	}*/

}
