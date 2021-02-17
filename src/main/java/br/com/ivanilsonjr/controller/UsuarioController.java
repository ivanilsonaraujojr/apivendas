package br.com.ivanilsonjr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivanilsonjr.model.Usuario;
import br.com.ivanilsonjr.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid Usuario usuario) {
		usuarioService.cadastrarUsuario(usuario);
		return ResponseEntity.created(null).build();
	}
}
