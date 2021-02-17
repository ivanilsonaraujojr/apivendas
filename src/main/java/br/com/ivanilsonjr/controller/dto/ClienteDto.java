package br.com.ivanilsonjr.controller.dto;


import org.springframework.data.domain.Page;

import br.com.ivanilsonjr.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String cpf;
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	
	public static Page<ClienteDto> converter(Page<Cliente> clientes){
		return clientes.map(c -> new ClienteDto(c));
	}
}
