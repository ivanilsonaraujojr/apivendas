package br.com.ivanilsonjr.controller.form;

import javax.validation.constraints.NotNull;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.Usuario;
import br.com.ivanilsonjr.model.Venda;
import br.com.ivanilsonjr.repository.AnuncioRepository;
import br.com.ivanilsonjr.repository.UsuarioRepository;

public class VendaForm {
	
	@NotNull
	private Long codigoAnuncio;
	@NotNull
	private Long compradorId;
	
	public Long getCodigoAnuncio() {
		return codigoAnuncio;
	}
	public void setCodigoAnuncio(Long codigoAnuncio) {
		this.codigoAnuncio = codigoAnuncio;
	}
	public Long getCompradorId() {
		return compradorId;
	}
	public void setCompradorId(Long compradorId) {
		this.compradorId = compradorId;
	}
	
	public Venda conveter(AnuncioRepository anuncioRepository, UsuarioRepository usuarioRepository) {
		Anuncio anuncio = anuncioRepository.findById(this.codigoAnuncio).get();
		Usuario comprador = usuarioRepository.getOne(this.compradorId);
		return new Venda(this.codigoAnuncio, anuncio, comprador , anuncio.getAnunciante(), anuncio.getPreco());
	}
}
