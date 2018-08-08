package com.dariosantos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dariosantos.cursomc.domain.Cliente;
import com.dariosantos.cursomc.dtos.ClienteDTO;
import com.dariosantos.cursomc.repositories.ClienteRepository;
import com.dariosantos.cursomc.services.exceptions.DataIntegrityException;
import com.dariosantos.cursomc.services.exceptions.ObjectNotFoundException;

import ch.qos.logback.core.net.server.Client;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	/*
	 * public Cliente insert(Cliente cliente) { cliente.setId(null); // garante que
	 * vai inserir e não atualizar return clienteRepository.save(cliente); }
	 */

	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId()); // para aproveitar
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que contem pedidos");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		// throw new UnsupportedOperationException("metodo não implementado");
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}
	
	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getNome());
	}

}
