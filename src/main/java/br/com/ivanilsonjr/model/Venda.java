package br.com.ivanilsonjr.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vendas")
public class Venda {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long codigo;
	@NotNull @OneToOne
	private Anuncio anuncioVendido;
	@ManyToOne @JoinColumn(name="id_cliente_comprador")
	private Cliente comprador;
	@ManyToOne @JoinColumn(name="id_cliente_vendedor")
	private Cliente vendedor;
	@NotNull
	private Double preco;
	private LocalDateTime dataCompra = LocalDateTime.now();
	
	public Venda() {
		
	}
	
	public Venda(Anuncio anuncioVendido, Cliente comprador, Cliente vendedor,
			Double preco) {
		this.anuncioVendido = anuncioVendido;
		this.comprador = comprador;
		this.vendedor = vendedor;
		this.preco = preco;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Anuncio getAnuncioVendido() {
		return anuncioVendido;
	}
	public void setAnuncioVendido(Anuncio anuncioVendido) {
		this.anuncioVendido = anuncioVendido;
	}
	public Cliente getComprador() {
		return comprador;
	}
	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}
	public Cliente getVendedor() {
		return vendedor;
	}
	public void setVendedor(Cliente vendedor) {
		this.vendedor = vendedor;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public LocalDateTime getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anuncioVendido == null) ? 0 : anuncioVendido.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (anuncioVendido == null) {
			if (other.anuncioVendido != null)
				return false;
		} else if (!anuncioVendido.equals(other.anuncioVendido))
			return false;
		return true;
	}
	
}
