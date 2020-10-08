package br.com.ivanilsonjr.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtualizacaoAnuncioForm {

	@NotEmpty @Size(min = 7, message="Titulo do anuncio invalido")
	private String titulo;
	@NotEmpty @Size(min = 20, message="Descrição do anuncio invalida")
	private String descricao;
	@NotNull
	private Double preco;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
