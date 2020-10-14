package br.com.ivanilsonjr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivanilsonjr.controller.dto.VendaDto;
import br.com.ivanilsonjr.service.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	private VendaService vs;
	
	@GetMapping
	public List<VendaDto> listar(){
		return vs.listarVendasTodas();
	}
}
