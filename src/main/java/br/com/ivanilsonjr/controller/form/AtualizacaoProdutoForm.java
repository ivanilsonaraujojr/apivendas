package br.com.ivanilsonjr.controller.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ivanilsonjr.model.enums.EstadoConservacao;

public class AtualizacaoProdutoForm {
	
	@NotNull @Enumerated(EnumType.STRING)
	private EstadoConservacao estadoConservacao;
	@NotEmpty @Size(min = 2, message="Nome do produto invalido")
	private String nome;
	
	public EstadoConservacao getEstadoConservacao() {
		return estadoConservacao;
	}
	public String getNome() {
		return nome;
	}
}
