package br.com.ivanilsonjr.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class AnuncioException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AnuncioException(String message) {
        super(message);
    }


}
