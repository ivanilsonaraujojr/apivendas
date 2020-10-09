package br.com.ivanilsonjr.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.controller.dto.AnuncioDto;
import br.com.ivanilsonjr.controller.form.AnuncioForm;
import br.com.ivanilsonjr.controller.form.AtualizacaoAnuncioForm;
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
	private UsuarioRepository ur;
	@Autowired 
	private ProdutoRepository pr;
	
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

		verificarAnuncioExistente(optional);

		AnuncioDto anuncio = new AnuncioDto(optional.get());
		return anuncio;
	}

	@Transactional
	public AnuncioDto atualizarAnuncio(Long codigo,AtualizacaoAnuncioForm atualizacaoAnuncioForm) {
		Optional<Anuncio> anuncio = ar.findById(codigo);

		verificarAnuncioExistente(anuncio);

		List<Anuncio> anunciosAtivosDoAnunciante = ar.findAllByStatusAndAnunciante(EstadoAnuncio.ABERTO, 
				   ur.findById(Long.parseLong("1")).get());

		if(!anunciosAtivosDoAnunciante.contains(anuncio.get())) {
			throw new BadRequestException("Esse anúncio não pertençe a você!");
		}

		//Atualizando entidade
		anuncio.get().setTitulo(atualizacaoAnuncioForm.getTitulo());
		anuncio.get().setDescricao(atualizacaoAnuncioForm.getDescricao());
		anuncio.get().setPreco(atualizacaoAnuncioForm.getPreco());

		AnuncioDto dto = new AnuncioDto(anuncio.get());
		return dto;
	}

	public AnuncioDto cadastrarAnuncio(AnuncioForm anuncioForm) {
		Anuncio anuncio = anuncioForm.converter(pr, ur);
		List<Anuncio> anunciosAtivosDoAnunciante = ar.findAllByStatusAndAnunciante(EstadoAnuncio.ABERTO, 
																				   anuncio.getAnunciante());
		List<Produto> produtosCadastradosAnunciante = pr.findAllByDonoProduto(anuncio.getAnunciante());

		if(!anunciosAtivosDoAnunciante.isEmpty() && anunciosAtivosDoAnunciante.contains(anuncio)) {
			throw new BadRequestException("Já existe um anúncio para esse produto!");
		}
		if(!produtosCadastradosAnunciante.contains(anuncio.getProduto())) {
			throw new BadRequestException("Produto inexistente ou não pertence a você, verifique o código do produto digitado!");
		}
		//Persistindo entidade
		ar.save(anuncio);
		AnuncioDto dto = new AnuncioDto(anuncio);

		return dto;
	}
	
	public void deletarAnuncioCodigo(Long codigo) {
		Optional<Anuncio> optional = ar.findById(codigo);

		verificarAnuncioExistente(optional);

		//Falta implementar lógica que verifica permissão de excluir o anuncio
		ar.delete(optional.get());
	}

	private void verificarAnuncioExistente(Optional<Anuncio> anuncio) {
		//Lança uma exceção(BadRequestException) se o anuncio nao existir no banco de dados
		if(!anuncio.isPresent()) {
			throw new BadRequestException("Anúncio inexistente, verifique o código digitado!");
		}
	}

}
