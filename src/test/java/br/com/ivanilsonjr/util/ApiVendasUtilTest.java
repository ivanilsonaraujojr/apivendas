package br.com.ivanilsonjr.util;


import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;

public class ApiVendasUtilTest {

@Test
public void testaMetodoDeVerificacaoDeOptional() {
	Optional<String> optional = Optional.empty();
	BadRequestException exception = assertThrows(BadRequestException.class, () -> {
		ApiVendasUtil.verificarObjetoEhExistente(optional, "String");
    });

	assertEquals("String inexistente, verifique o código digitado!", exception.getMessage());
}

}
