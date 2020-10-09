package br.com.ivanilsonjr.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.ivanilsonjr.model.Produto;
import br.com.ivanilsonjr.model.enums.EstadoConservacao;

public class ProdutoDto {

	private Long codigo;
	private String nome;
	@Enumerated(EnumType.STRING)
	private EstadoConservacao estadoConservacao;
	private UsuarioDto dono;

	public ProdutoDto(Produto produto) {
		this.codigo = produto.getCodigo();
		this.nome = produto.getNome();
		this.estadoConservacao = produto.getEstadoConservacao();
		this.dono = new UsuarioDto(produto.getDonoProduto());
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
	public UsuarioDto getDono() {
		return dono;
	}

	public static List<ProdutoDto> converter(List<Produto> produto){
		return produto.stream().map(a -> new ProdutoDto(a)).collect(Collectors.toList());
	}
}
