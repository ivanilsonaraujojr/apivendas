package br.com.ivanilsonjr.util;

import java.util.Optional;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;

public class ApiVendasUtil {

	private ApiVendasUtil() {
		throw new IllegalStateException("Classe de métodos utils");
	}
	  
	public static void verificarObjetoEhExistente(Optional<?> obj, String tipoObj) {
		if(!obj.isPresent()) {
			throw new BadRequestException(tipoObj + " inexistente, verifique o código digitado!");
		}
	}

}
