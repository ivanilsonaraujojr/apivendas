package br.com.ivanilsonjr.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ivanilsonjr.controller.dto.AnuncioDto;
import br.com.ivanilsonjr.controller.form.AnuncioForm;
import br.com.ivanilsonjr.controller.form.AtualizacaoAnuncioForm;
import br.com.ivanilsonjr.service.AnuncioService;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

	@Autowired
	private AnuncioService as;
	
	@GetMapping
	public List<AnuncioDto> listar(@RequestParam(required = true) boolean vendidos){
		if(vendidos == false) {
			return as.listarAnunciosAtivos();
		}else {
			return as.listarAnunciosTodos();
		}
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<AnuncioDto> mostrarAnuncio(@PathVariable Long codigo){
		AnuncioDto anuncio = as.mostrarAnuncioCodigo(codigo);
		if(anuncio != null) {
			return ResponseEntity.ok(anuncio);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	@Transactional
	public ResponseEntity<AnuncioDto> atualizarAnuncio(@PathVariable Long codigo, @Valid @RequestBody AtualizacaoAnuncioForm atualizacaoAnuncioForm){
		AnuncioDto dto = as.atualizarAnuncio(codigo, atualizacaoAnuncioForm);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<AnuncioDto> cadastrarAnuncio(@Valid @RequestBody AnuncioForm anuncioForm, UriComponentsBuilder uriBuilder){
		AnuncioDto dto = as.cadastrarAnuncio(anuncioForm);
		URI uri = uriBuilder.path("/anuncio/{id}").buildAndExpand(dto.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletarAnuncio(@PathVariable Long codigo){
		if(as.deletarAnuncioCodigo(codigo) != false) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

