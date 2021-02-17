package br.com.ivanilsonjr.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ivanilsonjr.model.Produto;
import br.com.ivanilsonjr.model.enums.EstadoConservacao;
import br.com.ivanilsonjr.repository.ClienteRepository;

public class ProdutoForm {
	
	@Size(min = 2, message="Nome do produto invalido")
	private String nome;
	@NotNull
	private EstadoConservacao estadoConservacao;
	
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
	
	public Produto converter(ClienteRepository clienteRepository) {
		return new Produto(nome, estadoConservacao, clienteRepository.findById(Long.parseLong("1")).get());
	}
}
