package br.com.ivanilsonjr.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.controller.dto.ProdutoDto;
import br.com.ivanilsonjr.controller.form.AtualizacaoProdutoForm;
import br.com.ivanilsonjr.controller.form.ProdutoForm;
import br.com.ivanilsonjr.model.Produto;
import br.com.ivanilsonjr.repository.ProdutoRepository;
import br.com.ivanilsonjr.repository.UsuarioRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private UsuarioRepository ur;

	public Page<ProdutoDto> listarProdutosTodos(Pageable paginacao){
		Page<Produto> listaProdutos = pr.findAll(paginacao);	
		Page<ProdutoDto> listaProdutosDto = ProdutoDto.converter(listaProdutos);
		return listaProdutosDto;
	}

	public ProdutoDto mostrarProdutoCodigo(Long codigo) {
		Optional<Produto> optional = pr.findById(codigo);

		verificarProdutoExistente(optional);

		ProdutoDto produto = new ProdutoDto(optional.get());
		return produto;
	}

	@Transactional
	public ProdutoDto atualizarProduto(Long codigo,AtualizacaoProdutoForm atualizacaoProdutoForm) {
		Optional<Produto> produto = pr.findById(codigo);

		verificarProdutoExistente(produto);
																 //Será incrementado depois com id gerado pelo token
		List<Produto> produtosCadastradosUsuario = pr.findAllByDonoProduto(ur.findById(Long.parseLong("1")).get());

		if(!produtosCadastradosUsuario.contains(produto.get())) {
			throw new BadRequestException("Esse produto não pertence a você!");
		}

		//Atualizando entidade
		produto.get().setNome(atualizacaoProdutoForm.getNome());
		produto.get().setEstadoConservacao(atualizacaoProdutoForm.getEstadoConservacao());

		ProdutoDto dto = new ProdutoDto(produto.get());
		return dto;
	}

	public ProdutoDto cadastrarProduto(ProdutoForm produtoForm) {
		Produto produto = produtoForm.converter(pr, ur);

		List<Produto> produtosCadastradosUsuario = pr.findAllByDonoProduto(ur.findById(Long.parseLong("1")).get());

		if(produtosCadastradosUsuario.contains(produto)) {
			throw new BadRequestException("Você ja cadastrou esse produto!");
		}

		pr.save(produto);

		ProdutoDto dto = new ProdutoDto(produto);
		return dto;
	}

	public void deletarProduto(Long codigo) {
		Optional<Produto> optional = pr.findById(codigo);

		verificarProdutoExistente(optional);

		pr.delete(optional.get());

	}

	private void verificarProdutoExistente(Optional<Produto> produto) {
		//Lança uma exceção(BadRequestException) se o produto nao existir no banco de dados
		if(!produto.isPresent()) {
			throw new BadRequestException("Produto inexistente, verifique o código digitado!");
		}
	}

}
