package br.com.ivanilsonjr.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ivanilsonjr.model.Usuario;

public class UsuarioDto {

	private String nome;
	private String email;
	
	public UsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	
	public static List<UsuarioDto> converter(List<Usuario> usuario){
		return usuario.stream().map(a -> new UsuarioDto(a)).collect(Collectors.toList());
	}
}
