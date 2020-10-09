package br.com.ivanilsonjr.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivanilsonjr.controller.dto.ProdutoDto;
import br.com.ivanilsonjr.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService ps;
	
	@GetMapping
	public List<ProdutoDto> listar(){
		return ps.listarProdutosTodos();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutoDto> mostrarProduto(@PathVariable Long codigo) {
		ProdutoDto produto = ps.mostrarProdutoCodigo(codigo);
			return ResponseEntity.ok(produto);
	}
}
