package br.com.ivanilsonjr.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.ivanilsonjr.model.Venda;

public class VendaDto {

	private Long codigo;
	private LocalDateTime dataCompra;
	private Long codigoAnuncioVendido;
	private Double preco;
	private String nomeVendedor;
	private String nomeComprador;

	public VendaDto() {

	}

	public VendaDto(Venda venda) {
		this.codigo = venda.getCodigo();
		this.dataCompra = venda.getDataCompra();
		this.codigoAnuncioVendido = venda.getAnuncioVendido().getCodigo();
		this.preco = venda.getPreco();
		this.nomeVendedor = venda.getVendedor().getNome();
		this.nomeComprador = venda.getComprador().getNome();
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
	public Double getPreco() {
		return preco;
	}
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public String getNomeComprador() {
		return nomeComprador;
	}
	
	public static Page<VendaDto> converter(Page<Venda> venda){
		return venda.map(v -> new VendaDto(v));
	}
}
