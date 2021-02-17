package br.com.ivanilsonjr.controller.dto;

import java.time.LocalDateTime;

import br.com.ivanilsonjr.model.Venda;
import br.com.ivanilsonjr.model.enums.EstadoConservacao;

public class DetalhesDaVendaDto {

	private Long codigo;
	private LocalDateTime dataCompra;
	private Long codigoAnuncioVendido;
	private String nomeProduto;
	private EstadoConservacao estadoConservacaoProduto;
	private Double preco;
	private ClienteDto vendedor;
	private ClienteDto comprador;
	
	public DetalhesDaVendaDto() {
		
	}

	public DetalhesDaVendaDto(Venda venda) {
		this.codigo = venda.getCodigo();
		this.dataCompra = venda.getDataCompra();
		this.codigoAnuncioVendido = venda.getAnuncioVendido().getCodigo();
		this.nomeProduto = venda.getAnuncioVendido().getProduto().getNome();
		this.estadoConservacaoProduto = venda.getAnuncioVendido().getProduto().getEstadoConservacao();
		this.preco = venda.getAnuncioVendido().getPreco();
		this.vendedor = new ClienteDto(venda.getVendedor());
		this.comprador = new ClienteDto(venda.getComprador());
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public Long getCodigoAnuncioVendido() {
		return codigoAnuncioVendido;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public EstadoConservacao getEstadoConservacaoProduto() {
		return estadoConservacaoProduto;
	}

	public Double getPreco() {
		return preco;
	}

	public ClienteDto getVendedor() {
		return vendedor;
	}

	public ClienteDto getComprador() {
		return comprador;
	}
	
}
