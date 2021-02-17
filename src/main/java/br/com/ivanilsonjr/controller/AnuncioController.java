package br.com.ivanilsonjr.controller;

import java.net.URI;

import javax.transaction.Transactional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ivanilsonjr.controller.dto.AnuncioDto;
import br.com.ivanilsonjr.controller.dto.DetalhesDoAnuncioDto;
import br.com.ivanilsonjr.controller.form.AnuncioForm;
import br.com.ivanilsonjr.controller.form.AtualizacaoAnuncioForm;
import br.com.ivanilsonjr.service.AnuncioService;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

	@Autowired
	private AnuncioService as;
	
	@GetMapping
	public Page<AnuncioDto> listar(@RequestParam(required = true) boolean vendidos, 
			@PageableDefault(size=15, sort={"codigo", "titulo"}) Pageable paginacao){
		if(!vendidos) {
			return as.listarAnunciosAtivos(paginacao);
		}else {
			return as.listarAnunciosTodos(paginacao);
		}
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<DetalhesDoAnuncioDto> mostrarAnuncio(@PathVariable Long codigo){
		DetalhesDoAnuncioDto anuncio = as.mostrarAnuncioCodigo(codigo);
		return ResponseEntity.ok(anuncio);
	}
	
	@PutMapping("/{codigo}")
	@Transactional
	public ResponseEntity<AnuncioDto> atualizarAnuncio(@PathVariable Long codigo, @Valid @RequestBody AtualizacaoAnuncioForm atualizacaoAnuncioForm){
		AnuncioDto dto = as.atualizarAnuncio(codigo, atualizacaoAnuncioForm);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<DetalhesDoAnuncioDto> cadastrarAnuncio(@Valid @RequestBody AnuncioForm anuncioForm, UriComponentsBuilder uriBuilder){
		DetalhesDoAnuncioDto dto = as.cadastrarAnuncio(anuncioForm);
		URI uri = uriBuilder.path("/anuncio/{id}").buildAndExpand(dto.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletarAnuncio(@PathVariable Long codigo){
		as.deletarAnuncioCodigo(codigo);
		return ResponseEntity.ok().build();
	}

}

