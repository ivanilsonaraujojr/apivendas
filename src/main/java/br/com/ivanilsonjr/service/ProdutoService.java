package br.com.ivanilsonjr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
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

	public ProdutoDto mostrarProdutoCodigo(Long codigo) {
		Optional<Produto> optional = pr.findById(codigo);

		verificarProdutoExistente(optional);

		ProdutoDto produto = new ProdutoDto(optional.get());
		return produto;
	}

	private void verificarProdutoExistente(Optional<Produto> produto) {
		//Lança uma exceção(BadRequestException) se o produto nao existir no banco de dados
		if(!produto.isPresent()) {
			throw new BadRequestException("Produto inexistente, verifique o codigo digitado!");
		}
	}
}
