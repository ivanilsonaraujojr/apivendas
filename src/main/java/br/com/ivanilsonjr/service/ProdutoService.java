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
import br.com.ivanilsonjr.repository.ClienteRepository;
import br.com.ivanilsonjr.repository.ProdutoRepository;
import br.com.ivanilsonjr.util.ApiVendasUtil;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private ClienteRepository cr;

	public Page<ProdutoDto> listarProdutosTodos(Pageable paginacao){
		Page<Produto> listaProdutos = pr.findAll(paginacao);	
		return ProdutoDto.converter(listaProdutos);
	}

	public ProdutoDto mostrarProdutoCodigo(Long codigo) {
		Optional<Produto> optional = pr.findById(codigo);

		ApiVendasUtil.verificarObjetoEhExistente(optional,"Produto");

		return new ProdutoDto(optional.get());
	}

	@Transactional
	public ProdutoDto atualizarProduto(Long codigo,AtualizacaoProdutoForm atualizacaoProdutoForm) {
		Optional<Produto> produto = pr.findById(codigo);

		ApiVendasUtil.verificarObjetoEhExistente(produto,"Produto");

		List<Produto> produtosCadastradosUsuario = pr.findAllByDonoProduto(cr.findById(Long.parseLong("1")).get());

		if(!produtosCadastradosUsuario.contains(produto.get())) {
			throw new BadRequestException("Esse produto não pertence a você!");
		}

		//Atualizando entidade
		produto.get().setNome(atualizacaoProdutoForm.getNome());
		produto.get().setEstadoConservacao(atualizacaoProdutoForm.getEstadoConservacao());

		return new ProdutoDto(produto.get());
	}

	public ProdutoDto cadastrarProduto(ProdutoForm produtoForm) {
		Produto produto = produtoForm.converter(cr);

		List<Produto> produtosCadastradosUsuario = pr.findAllByDonoProduto(cr.findById(Long.parseLong("1")).get());

		if(produtosCadastradosUsuario.contains(produto)) {
			throw new BadRequestException("Você ja cadastrou esse produto!");
		}

		pr.save(produto);

		return new ProdutoDto(produto);
	}

	public void deletarProduto(Long codigo) {
		Optional<Produto> optional = pr.findById(codigo);

		ApiVendasUtil.verificarObjetoEhExistente(optional,"Produto");

		pr.delete(optional.get());

	}

}
