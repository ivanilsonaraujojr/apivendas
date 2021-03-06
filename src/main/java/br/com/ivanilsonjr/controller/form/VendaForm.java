package br.com.ivanilsonjr.controller.form;

import javax.validation.constraints.NotNull;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.Cliente;
import br.com.ivanilsonjr.model.Venda;
import br.com.ivanilsonjr.repository.AnuncioRepository;
import br.com.ivanilsonjr.repository.ClienteRepository;

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
	
	public Venda conveter(AnuncioRepository anuncioRepository, ClienteRepository clienteRepository) {
		Anuncio anuncio = anuncioRepository.findById(this.codigoAnuncio).get();
		Cliente comprador = clienteRepository.getOne(this.compradorId);
		return new Venda(anuncio, comprador , anuncio.getAnunciante(), anuncio.getPreco());
	}
}
