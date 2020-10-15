package br.com.ivanilsonjr.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;

public class AnuncioDto {

	private Long codigo;
	private String titulo;
	private EstadoAnuncio status;
	private Double preco;
	private String donoAnuncioNome;
	private String produtoNome;
	private LocalDateTime dataAnuncio;
	private LocalDateTime dataVenda;
	
	public AnuncioDto(Anuncio anuncio) {
		this.codigo = anuncio.getCodigo();
		this.titulo = anuncio.getTitulo();
		this.status = anuncio.getStatus();
		this.preco = anuncio.getPreco();
		this.donoAnuncioNome = anuncio.getAnunciante().getNome();
		this.produtoNome = anuncio.getProduto().getNome();
		this.dataAnuncio = anuncio.getDataAnuncio();
		this.dataVenda = anuncio.getDataVenda();
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public EstadoAnuncio getStatus() {
		return status;
	}
	public Double getPreco() {
		return preco;
	}
	public String getDonoAnuncioNome() {
		return donoAnuncioNome;
	}
	public String getProdutoNome() {
		return produtoNome;
	}
	public LocalDateTime getDataAnuncio() {
		return dataAnuncio;
	}
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}
	
	public static Page<AnuncioDto> converter(Page<Anuncio> anuncio){
		return anuncio.map(a -> new AnuncioDto(a));
	}
	
}
