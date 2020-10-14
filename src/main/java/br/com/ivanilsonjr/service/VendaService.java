package br.com.ivanilsonjr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.controller.dto.DetalhesDaVendaDto;
import br.com.ivanilsonjr.controller.dto.VendaDto;
import br.com.ivanilsonjr.model.Venda;
import br.com.ivanilsonjr.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vr;
	
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

	private void verificarVendaExistente(Optional<Venda> venda) {
		if(!venda.isPresent()) {
			throw new BadRequestException("Anúncio inexistente, verifique o código digitado!");
		}

	}

}
