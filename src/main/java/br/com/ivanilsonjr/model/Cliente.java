package br.com.ivanilsonjr.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;
    @NotEmpty(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;
    @OneToMany(mappedBy = "anunciante",fetch = FetchType.LAZY)
    private List<Anuncio> anuncios;
    @OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY)
    private List<Venda> vendas;
    @OneToMany(mappedBy = "comprador", fetch = FetchType.LAZY)
    private List<Venda> compras;

    public Cliente() {
    }

    public Cliente(String nome,String cpf) {
    	this.nome = nome;
    	this.cpf = cpf;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<Anuncio> getAnuncios() {
		return anuncios;
	}
	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	public List<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public List<Venda> getCompras() {
		return compras;
	}
	public void setCompras(List<Venda> compras) {
		this.compras = compras;
	}
}
