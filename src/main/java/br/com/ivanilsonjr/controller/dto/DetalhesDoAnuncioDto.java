package br.com.ivanilsonjr.controller.dto;

import java.time.LocalDateTime;


import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;

public class DetalhesDoAnuncioDto {

	private Long codigo;
	private String titulo;
	private String descricao;
	private EstadoAnuncio status;
	private Double preco;
	private ProdutoDto produto;
	private ClienteDto anunciante;
	private LocalDateTime dataAnuncio;
	private LocalDateTime dataVenda;
	
	public DetalhesDoAnuncioDto(Anuncio anuncio) {
		this.codigo = anuncio.getCodigo();
		this.titulo = anuncio.getTitulo();
		this.descricao = anuncio.getDescricao();
		this.status = anuncio.getStatus();
		this.preco = anuncio.getPreco();
		this.produto = new ProdutoDto(anuncio.getProduto());
		this.anunciante = new ClienteDto(anuncio.getAnunciante());
		this.dataAnuncio = anuncio.getDataAnuncio();
		this.dataVenda = anuncio.getDataVenda();
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public EstadoAnuncio getStatus() {
		return status;
	}
	public Double getPreco() {
		return preco;
	}
	public ProdutoDto getProduto() {
		return produto;
	}
	public ClienteDto getAnunciante() {
		return anunciante;
	}
	public LocalDateTime getDataAnuncio() {
		return dataAnuncio;
	}
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}
	
}
