package br.com.ivanilsonjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.repository.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository ar;
	
	public List<Anuncio> listarAnuncios(){
		return ar.findAll();
	}
	
}
