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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ivanilsonjr.controller.dto.DetalhesDaVendaDto;
import br.com.ivanilsonjr.controller.dto.VendaDto;
import br.com.ivanilsonjr.controller.form.VendaForm;
import br.com.ivanilsonjr.service.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	private VendaService vs;
	
	@GetMapping
	public Page<VendaDto> listar(@PageableDefault(sort={"codigo","dataCompra"},size=15)Pageable paginacao){
		return vs.listarVendasTodas(paginacao);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<DetalhesDaVendaDto> mostrarVenda(@PathVariable Long codigo){
		DetalhesDaVendaDto venda = vs.mostrarVendaCodigo(codigo);
		return ResponseEntity.ok(venda);
	}

	@PostMapping
	public ResponseEntity<VendaDto> cadastrarVenda(@RequestBody @Valid VendaForm vendaForm, UriComponentsBuilder uriBuilder){
		VendaDto dto = vs.cadastrarVenda(vendaForm);
		URI uri = uriBuilder.path("/venda/{codigo}").buildAndExpand(dto.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletarVenda(@PathVariable Long codigo){
		vs.deletarVenda(codigo);
		return ResponseEntity.ok().build();
	}
}
