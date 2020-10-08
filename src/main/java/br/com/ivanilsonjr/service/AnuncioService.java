package br.com.ivanilsonjr.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.AnuncioException;
import br.com.ivanilsonjr.controller.dto.AnuncioDto;
import br.com.ivanilsonjr.controller.form.AnuncioForm;
import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.Produto;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;
import br.com.ivanilsonjr.repository.AnuncioRepository;
import br.com.ivanilsonjr.repository.ProdutoRepository;
import br.com.ivanilsonjr.repository.UsuarioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository ar;
	@Autowired
	UsuarioRepository ur;
	@Autowired 
	ProdutoRepository pr;
	
	public List<AnuncioDto> listarAnunciosTodos(){
		List<Anuncio> listaAnuncios = ar.findAll();
		List<AnuncioDto> ListaAnunciosDto = AnuncioDto.converter(listaAnuncios);
		return ListaAnunciosDto;
	}

	public List<AnuncioDto> listarAnunciosAtivos() {
		List<Anuncio> listaAnuncios = ar.findAllByStatus(EstadoAnuncio.ABERTO);
		List<AnuncioDto> ListaAnunciosDto = AnuncioDto.converter(listaAnuncios);
		return ListaAnunciosDto;
	}
	
	public AnuncioDto mostrarAnuncioCodigo(Long codigo) {
		Optional<Anuncio> optional = ar.findById(codigo);
		if(optional.isPresent()) {
			AnuncioDto anuncio = new AnuncioDto(optional.get());
			return anuncio;
		}
		return null;
	}
	
	public boolean deletarAnuncioCodigo(Long codigo) {
		Optional<Anuncio> optional = ar.findById(codigo);
		if(optional.isPresent()) {
			ar.delete(optional.get());
			return true;
		}
		return false;
	}

	public Anuncio cadastrarAnuncio(@Valid AnuncioForm anuncioForm) {
		Anuncio anuncio = anuncioForm.converter(pr, ur);
		List<Anuncio> anunciosAtivosDoAnunciante = ar.findAllByStatusAndAnunciante(EstadoAnuncio.ABERTO, anuncio.getAnunciante());
		List<Produto> produtosCadastradosAnunciante = pr.findAllByDonoProduto(anuncio.getAnunciante());

		if(!anunciosAtivosDoAnunciante.isEmpty() && anunciosAtivosDoAnunciante.contains(anuncio)) {
			throw new AnuncioException("Anuncio ja existente!");
		}
		if(!produtosCadastradosAnunciante.isEmpty() && !produtosCadastradosAnunciante.contains(anuncio.getProduto())) {
			throw new AnuncioException("Produto inexistente ou ele não pertence a você!");
		}
	
		ar.save(anuncio);
		return anuncio;
	}
	
}
