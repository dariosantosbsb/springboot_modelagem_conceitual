package com.dariosantos.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dariosantos.cursomc.domain.Categoria;
import com.dariosantos.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	Page<Produto> search(String nome, List<Categoria> categorias, Pageable pageRequest);
		
	
	
}
