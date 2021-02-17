package br.com.ivanilsonjr.controller.dto;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.domain.Page;

import br.com.ivanilsonjr.model.Produto;
import br.com.ivanilsonjr.model.enums.EstadoConservacao;

public class ProdutoDto {

	private Long codigo;
	private String nome;
	@Enumerated(EnumType.STRING)
	private EstadoConservacao estadoConservacao;
	private ClienteDto dono;

	public ProdutoDto(Produto produto) {
		this.codigo = produto.getCodigo();
		this.nome = produto.getNome();
		this.estadoConservacao = produto.getEstadoConservacao();
		this.dono = new ClienteDto(produto.getDonoProduto());
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public EstadoConservacao getEstadoConservacao() {
		return estadoConservacao;
	}
	public ClienteDto getDono() {
		return dono;
	}

	public static Page<ProdutoDto> converter(Page<Produto> produto){
		return produto.map(p -> new ProdutoDto(p));
	}
}
