package br.com.ivanilsonjr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.ivanilsonjr.model.enums.EstadoAnuncio;

@Entity
@Table(name="anuncios")
public class Anuncio {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long codigo;
	@NotNull @NotEmpty
	private String titulo;
	@OneToOne
	private Produto produto;
	@NotNull @Enumerated(EnumType.STRING)
	private EstadoAnuncio status = EstadoAnuncio.ABERTO;
	@NotNull @NotEmpty
	private Double preco;
	@OneToOne
	private Usuario anunciante;
	@NotNull
	private Date dataAnuncio;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Usuario getAnunciante() {
		return anunciante;
	}
	public void setAnunciante(Usuario anunciante) {
		this.anunciante = anunciante;
	}
	public EstadoAnuncio getStatus() {
		return status;
	}
	public void setStatus(EstadoAnuncio status) {
		this.status = status;
	}
	public Date getDataAnuncio() {
		return dataAnuncio;
	}
	public void setDataAnuncio(Date dataAnuncio) {
		this.dataAnuncio = dataAnuncio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anunciante == null) ? 0 : anunciante.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		Anuncio other = (Anuncio) obj;
		if (anunciante == null) {
			if (other.anunciante != null)
				return false;
		} else if (!anunciante.equals(other.anunciante))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
}