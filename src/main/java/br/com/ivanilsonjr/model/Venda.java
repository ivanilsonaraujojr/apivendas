package br.com.ivanilsonjr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vendas")
public class Venda {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long codigo;
	@NotNull @OneToOne
	private Anuncio anuncioVendido;
	@ManyToOne @JoinColumn(name="usuario_comprador_id")
	private Usuario compradorId;
	@ManyToOne @JoinColumn(name="usuario_vendedor_id")
	private Usuario vendedorId;
	@NotNull @NotEmpty
	private Double preco;
	@NotNull
	private Date dataCompra;
	
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
	public Usuario getCompradorId() {
		return compradorId;
	}
	public void setCompradorId(Usuario compradorId) {
		this.compradorId = compradorId;
	}
	public Usuario getVendedorId() {
		return vendedorId;
	}
	public void setVendedorId(Usuario vendedorId) {
		this.vendedorId = vendedorId;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
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
