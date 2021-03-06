package br.com.ivanilsonjr.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;

@RestControllerAdvice
public class ErroDeValidacaoHandler{
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception){
		List<ErroDeFormularioDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), e.getDefaultMessage());
			dto.add(erro);
		});
		return dto;
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=JsonProcessingException.class)
	public ErroDeJsonDto jsonHandle(JsonProcessingException exception) {
		return new ErroDeJsonDto("Erro: Verifique seu JSON", "Linha: " + exception.getLocation().getLineNr());
	}

	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=BadRequestException.class)
	public ErroGenericoDto erroDeDados(BadRequestException exception) {
		return new ErroGenericoDto(exception.getMessage());
	}
}
