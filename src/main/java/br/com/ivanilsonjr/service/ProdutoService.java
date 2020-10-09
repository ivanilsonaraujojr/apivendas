package br.com.ivanilsonjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.controller.dto.ProdutoDto;
import br.com.ivanilsonjr.model.Produto;
import br.com.ivanilsonjr.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository pr;
	
	public List<ProdutoDto> listarProdutosTodos(){
		List<Produto> listaProdutos = pr.findAll();	
		List<ProdutoDto> listaProdutosDto = ProdutoDto.converter(listaProdutos);
		return listaProdutosDto;
	}
}
