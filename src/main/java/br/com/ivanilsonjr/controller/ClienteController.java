package br.com.ivanilsonjr.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ivanilsonjr.controller.dto.ClienteDto;
import br.com.ivanilsonjr.controller.form.AtualizacaoClienteForm;
import br.com.ivanilsonjr.controller.form.ClienteForm;
import br.com.ivanilsonjr.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService cs;
	
	@GetMapping
	public Page<ClienteDto> listar(@PageableDefault(size=25, sort={"id", "nome"}) Pageable paginacao){
		return cs.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> mostrarCliente(@PathVariable Long id){
		ClienteDto cliente = cs.mostrarClienteCodigo(id);
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @Valid @RequestBody AtualizacaoClienteForm atualizacaoClienteForm){
		ClienteDto clienteAtualizadoDto = cs.atualizarCliente(id, atualizacaoClienteForm);
		return ResponseEntity.ok(clienteAtualizadoDto);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm){
		ClienteDto clienteDto = cs.cadastrarCliente(clienteForm);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDto.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCliente(@PathVariable Long id){
		cs.deletarCliente(id);
		return ResponseEntity.ok().build();
	}
}
