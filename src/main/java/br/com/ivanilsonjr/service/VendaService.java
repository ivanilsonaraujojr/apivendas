package br.com.ivanilsonjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
