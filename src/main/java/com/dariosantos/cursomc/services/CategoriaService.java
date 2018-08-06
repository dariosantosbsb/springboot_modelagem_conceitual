package com.dariosantos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dariosantos.cursomc.domain.Categoria;
import com.dariosantos.cursomc.repositories.CategoriaRepository;
import com.dariosantos.cursomc.services.exceptions.DataIntegrityException;
import com.dariosantos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null); // garante que vai inserir e não atualizar
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId()); // para aproveitar
		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que contem produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return categoriaRepository.findAll(pageRequest);
	}

}
