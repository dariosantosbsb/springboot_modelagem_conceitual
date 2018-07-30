package com.dariosantos.cursomc;

//import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dariosantos.cursomc.domain.Categoria;
import com.dariosantos.cursomc.domain.Produto;
import com.dariosantos.cursomc.domain.Estado;
import com.dariosantos.cursomc.domain.Cidade;
import com.dariosantos.cursomc.repositories.CategoriaRepository;
import com.dariosantos.cursomc.repositories.CidadeRepository;
import com.dariosantos.cursomc.repositories.EstadoRepository;
import com.dariosantos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");

		Produto p1 = new Produto(null, "pc", 2000.00);
		Produto p2 = new Produto(null, "impressora", 3000.00);
		Produto p3 = new Produto(null, "mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");

		// associação muitos pra um já é feita no próprio construtor
		Cidade c1 = new Cidade(null, "Uberlândia", est2);
		Cidade c2 = new Cidade(null, "São Paulo", est1);
		Cidade c3 = new Cidade(null, "Camplinas", est1);

		est1.getCidades().addAll(Arrays.asList(c2, c3));
		est2.getCidades().addAll(Arrays.asList(c1));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

	}
}
