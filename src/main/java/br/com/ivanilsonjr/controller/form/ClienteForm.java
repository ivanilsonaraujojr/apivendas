package br.com.ivanilsonjr.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import br.com.ivanilsonjr.model.Cliente;

public class ClienteForm {

	@NotEmpty(message = "Nome é obrigatório")
	private String nome;
	@NotEmpty @CPF(message = "CPF inválido")
	private String cpf;
	
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
	
	public Cliente converter(){
		return new Cliente(nome,cpf);
	}
}
