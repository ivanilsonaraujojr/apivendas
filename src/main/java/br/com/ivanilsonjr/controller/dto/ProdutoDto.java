package br.com.ivanilsonjr.controller.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.ivanilsonjr.model.Produto;
import br.com.ivanilsonjr.model.enums.EstadoConservacao;

public class ProdutoDto {

	private Long codigo;
	private String nome;
	@Enumerated(EnumType.STRING)
	private EstadoConservacao estadoConservacao;
	public ProdutoDto(Produto produto) {
		this.codigo = produto.getCodigo();
		this.nome = produto.getNome();
		this.estadoConservacao = produto.getEstadoConservacao();
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
	
}
