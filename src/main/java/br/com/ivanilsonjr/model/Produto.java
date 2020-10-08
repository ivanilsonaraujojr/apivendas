package br.com.ivanilsonjr.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.ivanilsonjr.model.enums.EstadoConservacao;

@Entity
@Table(name="produtos")
public class Produto {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long codigo;
	@NotNull @NotEmpty
	private String nome;
	@NotNull @Enumerated(EnumType.STRING)
	private EstadoConservacao estadoConservacao;
	@ManyToOne
	private Usuario donoProduto;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public EstadoConservacao getEstadoConservacao() {
		return estadoConservacao;
	}
	public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}
	public Usuario getDonoProduto() {
		return donoProduto;
	}
	public void setDonoProduto(Usuario donoProduto) {
		this.donoProduto = donoProduto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}