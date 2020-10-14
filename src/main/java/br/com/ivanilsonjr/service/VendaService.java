package br.com.ivanilsonjr.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.controller.dto.DetalhesDaVendaDto;
import br.com.ivanilsonjr.controller.dto.VendaDto;
import br.com.ivanilsonjr.controller.form.VendaForm;
import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.Venda;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;
import br.com.ivanilsonjr.repository.AnuncioRepository;
import br.com.ivanilsonjr.repository.UsuarioRepository;
import br.com.ivanilsonjr.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vr;
	@Autowired
	private AnuncioRepository ar;
	@Autowired
	private UsuarioRepository ur;
	
	public List<VendaDto> listarVendasTodas() {
		List<Venda> listaVendas = vr.findAll();
		List<VendaDto> listaVendasDto = VendaDto.converter(listaVendas);
		return listaVendasDto;
	}

	public DetalhesDaVendaDto mostrarVendaCodigo(Long codigo) {
		Optional<Venda> optional = vr.findById(codigo);

		verificarVendaExistente(optional);

		DetalhesDaVendaDto venda = new DetalhesDaVendaDto(optional.get());
		return venda;
	}
	
	@Transactional
	public VendaDto cadastrarVenda(VendaForm vendaForm) {
		//Verifica existência do anuncio
		Optional<Anuncio> anuncioVendido = ar.findById(vendaForm.getCodigoAnuncio());
		if(!anuncioVendido.isPresent()) {
			throw new BadRequestException("Produto inexistente, verifique o código digitado!");
		}
		
		if(anuncioVendido.get().getStatus().equals(EstadoAnuncio.VENDIDO)) {
			throw new BadRequestException("Esse produto ja foi vendido, verifique o código digitado!");
		}
		
		//Se existir, registrar a venda muda o status do anuncio para vendido e atribui data da venda
		Venda venda = vendaForm.conveter(ar, ur);
		
		//Persiste a venda no banco de dados
		vr.save(venda);
		
		anuncioVendido.get().setStatus(EstadoAnuncio.VENDIDO);
		anuncioVendido.get().setDataVenda(venda.getDataCompra());
		
		VendaDto dto = new VendaDto(venda);
		return dto;
	}

	private void verificarVendaExistente(Optional<Venda> venda) {
		if(!venda.isPresent()) {
			throw new BadRequestException("Venda inexistente, verifique o código digitado!");
		}
	}

}
