package br.com.ivanilsonjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.controller.dto.AnuncioDto;
import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.repository.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository ar;
	
	public List<AnuncioDto> listarAnuncios(){
		List<Anuncio> listaAnuncios = ar.findAll();
		List<AnuncioDto> ListaAnunciosDto = AnuncioDto.converter(listaAnuncios);
		return ListaAnunciosDto;
	}
	
}
