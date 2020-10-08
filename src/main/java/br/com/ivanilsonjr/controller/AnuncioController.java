package br.com.ivanilsonjr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.service.AnuncioService;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

	@Autowired
	private AnuncioService as;
	
	@GetMapping
	public List<Anuncio> listar(){
		return as.listarAnuncios();
	}
}
