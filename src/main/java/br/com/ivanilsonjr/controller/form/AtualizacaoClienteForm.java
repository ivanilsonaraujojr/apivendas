package br.com.ivanilsonjr.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

public class AtualizacaoClienteForm {

	@NotEmpty(message = "Nome é obrigatório")
	private String nome;
	@NotEmpty @CPF(message = "CPF inválido")
	private String cpf;

	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	
}
