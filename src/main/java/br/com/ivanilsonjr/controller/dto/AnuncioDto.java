package br.com.ivanilsonjr.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;

public class AnuncioDto {

	private Long codigo;
	private String titulo;
	private String descricao;
	private EstadoAnuncio status;
	private Double preco;
	private ProdutoDto produto;
	private UsuarioDto anunciante;
	private LocalDateTime dataAnuncio;
	private LocalDateTime dataVenda;
	
	public AnuncioDto(Anuncio anuncio) {
		this.codigo = anuncio.getCodigo();
		this.titulo = anuncio.getTitulo();
		this.descricao = anuncio.getDescricao();
		this.status = anuncio.getStatus();
		this.preco = anuncio.getPreco();
		this.produto = new ProdutoDto(anuncio.getProduto());
		this.anunciante = new UsuarioDto(anuncio.getAnunciante());
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
	public UsuarioDto getAnunciante() {
		return anunciante;
	}
	public LocalDateTime getDataAnuncio() {
		return dataAnuncio;
	}
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public static List<AnuncioDto> converter(List<Anuncio> anuncio){
		return anuncio.stream().map(a -> new AnuncioDto(a)).collect(Collectors.toList());
	}
}